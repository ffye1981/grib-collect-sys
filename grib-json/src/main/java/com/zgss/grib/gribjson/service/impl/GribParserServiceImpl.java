package com.zgss.grib.gribjson.service.impl;

import com.zgss.grib.common.util.FileUtil;
import com.zgss.grib.gribjson.entity.Scheme;
import com.zgss.grib.gribjson.service.IGribParseService;
import com.zgss.grib.gribjson.service.IMongoService;
import com.zgss.grib.gribjson.service.JsonMsgProducer;
import com.zgss.grib.gribjson.config.SchemeManager;
import com.zgss.grib.gribjson.util.Launcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class GribParserServiceImpl implements IGribParseService {
    private Logger logger = LoggerFactory.getLogger(GribParserServiceImpl.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Value("${dataRoot}")
    private String dataRoot;

    @Autowired
    SchemeManager schemeManager;

    @Autowired
    JsonMsgProducer jsonMsgProducer;

    @Autowired
    IMongoService mongoService;

    @Override
    @Scheduled(cron = "${cron.toJson}")
    public void parse() {
        if(!FileUtil.isFileExist(this.dataRoot)) {
            logger.info("本地数据目录["+ this.dataRoot+ "不存在,即将退出。");
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        String dataStore = sdf.format(calendar.getTime());
        dataStore = this.dataRoot + File.separator + dataStore;
        if(!FileUtil.isFileExist(dataStore)) {
            logger.info("今日数据目录:"+ dataStore +"还未生成,即将退出。");
            return;
        }
        String jsonStore = dataStore + File.separator + "json";
        if(FileUtil.isFileExist(jsonStore)) {
            logger.info("今日数据已经解析完毕，即将退出。");
            return;
        }
        FileUtil.makeDirectory(jsonStore);
        String[] files = FileUtil.listNames(dataStore,"gfs.t12z.pgrb2");
        List<Scheme> schemes = this.schemeManager.getSchemes();
        String gribName,jsonName,args;
        boolean error = false;
        for (String file: files) {
            gribName = dataStore + File.separator + file;
            for (Scheme scheme: schemes){
                jsonName = jsonStore+ File.separator +file + "_" + scheme.getSubject()+"_"+scheme.getFv() + ".json";
                args = "--names --data --fc "+scheme.getFc()+ " --fp "+ scheme.getFp()+" --fv "+ scheme.getFv()+" --fs "+scheme.getFs()
                        +" --output "+jsonName +" "+gribName;
                try {
                    //解析Grib文件位json文件
                    Launcher.main(args.split(" "));
                    //json文件入库
                    mongoService.insertJsonFile(jsonName,scheme.getSubject());
                    //解析json文件为grid格点数据
                    jsonMsgProducer.sendMessage(jsonName);
                    logger.info("girb文件：" + gribName + "解析为："+ jsonName + "完毕。");
                }catch (Exception e) {
                    logger.info("girb文件：" + gribName + "解析为："+ jsonName + "时发生错误：" + e.getMessage());
                    e.printStackTrace();
                    error = true;
                    break;
                }
                logger.info(args);
            }
            if(error) {
                break;
            }
        }
        logger.info("今日数据解析完成...");
    }


    public String getDataRoot() {
        return dataRoot;
    }
    public void setDataRoot(String dataStore) {
        this.dataRoot = dataRoot;
    }
}
