package com.zgss.girb.gribdownload.service.impl;

import com.zgss.girb.gribdownload.entity.Task;
import com.zgss.girb.gribdownload.ftp.FtpConfig;
import com.zgss.girb.gribdownload.ftp.FtpUtil;
import com.zgss.girb.gribdownload.service.IGribService;
import com.zgss.girb.gribdownload.service.TaskManager;
import com.zgss.grib.common.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class GribServiceImpl implements IGribService {
    private Logger logger = LoggerFactory.getLogger(GribServiceImpl.class);

    @Autowired
    FtpConfig ftpConfig;

    @Autowired
    FtpUtil ftpUtil;

    @Value("${localDir}")
    private String localDir;

    @Autowired
    TaskManager taskManager;

//    @Scheduled(fixedDelay=1000)
    public void downLoadTest1() {
        String remotePath = "/pub/data/nccf/com/gfs/prod/gfs.20190704/18";
        String localPath = "E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库";
        List<String> fileNames = new ArrayList<String>();
        fileNames.add("gfs.t18z.pgrb2.1p00.f000");
        this.ftpUtil.downloadFile(remotePath,fileNames,localPath);
//        String lastDayPath = this.ftpUtil.getLastDaySub(ftpConfig.getRoot());
//        logger.info("lastDayPath：" +lastDayPath);

    }

//    @Scheduled(fixedDelay=1000)
    public void downLoad() {
        //获取最新的数据目录
        String lastDayPath = this.ftpUtil.getLastDaySub(ftpConfig.getRoot());
        String lastHourPath = this.ftpUtil.getLastHourSub(ftpConfig.getRoot()+ File.separator + lastDayPath);
        logger.info("lastHourPath：" +lastHourPath);
        logger.info("lastDayPath：" +lastDayPath);
        String remotePath = ftpConfig.getRoot()+ File.separator + lastDayPath + File.separator + lastHourPath;
        String localPath = this.localDir + File.separator + lastDayPath + File.separator + lastHourPath;
        if(FileUtil.isFileExist(localPath)) {
            logger.info("最新数据已经下载，即将退出。。。");
            return;
        }
        //最新的数据目录是否已经上传完毕
        int gribCnt = this.ftpUtil.cntFiles(remotePath,"gfs.t"+lastHourPath+"z.pgrb2.1p00.f",".idx");
        if(gribCnt < 129) {
            logger.info("最新数据目录["+ remotePath+ "]数据还未上传完毕,当前数量："+ gribCnt);
            return;
        }
        if(this.localDir!=null && !this.localDir.isEmpty()) {
            if(!FileUtil.makeDirectory(localPath)){
                logger.info("本地目录["+ localPath+ "]创建失败");
                return;
            };
            logger.info("最新数据目录["+ remotePath+ "]数据已经上传完毕,即将开始下载。");
            List<String> fileNames = new ArrayList<String>();
            for(int i = 0;i <= 384;i = i + 3) {
                String fileName = "gfs.t"+lastHourPath+"z.pgrb2.1p00.f" + String.format("%0" + 3 + "d", i);
                fileNames.add(fileName);
            }
            this.ftpUtil.downloadFile(remotePath,fileNames,localPath);
            logger.info("最新数据目录["+ remotePath+ "]数据下载完毕。");
        }else {
            logger.info("本地目录["+ this.localDir+ "]未配置，请配置。");
        }

    }

    @Override
    public void downLoadTest() {

    }

    public void downLoadFilter() {
        //获取最新的数据目录
        String lastDayPath = this.ftpUtil.getLastDaySub(ftpConfig.getRoot());
        lastDayPath = "gfs.20190707";
        String lastHourPath = this.ftpUtil.getLastHourSub(ftpConfig.getRoot()+ "/" + lastDayPath);
        logger.info("lastHourPath：" +lastHourPath);
        logger.info("lastDayPath：" +lastDayPath);
        String remotePath = ftpConfig.getRoot()+ "/" + lastDayPath + "/" + lastHourPath;
        String localPath = this.localDir + File.separator + lastDayPath + File.separator + lastHourPath;
        if(FileUtil.isFileExist(localPath)) {
            logger.info("最新数据已经下载，即将退出。。。");
            return;
        }
        //最新的数据目录是否已经上传完毕
        int gribCnt = this.ftpUtil.cntFiles(remotePath,"gfs.t"+lastHourPath+"z.pgrb2.1p00.f",".idx");
        if(gribCnt < 129) {
            logger.info("最新数据目录["+ remotePath+ "]数据还未上传完毕,当前数量："+ gribCnt);
            return;
        }
        if(this.localDir!=null && !this.localDir.isEmpty()) {
            if(!FileUtil.makeDirectory(localPath)){
                logger.info("本地目录["+ localPath+ "]创建失败");
                return;
            };
            logger.info("最新数据目录["+ remotePath+ "]数据已经上传完毕,即将开始下载。");
            List<String> fileNames = new ArrayList<String>();
            for(int i = 0;i <= 168; i = i + 3) {
//                for(int i = 0;i <= 384;i = i + 3) {
                String fileName = "gfs.t"+lastHourPath+"z.pgrb2.1p00.f" + String.format("%0" + 3 + "d", i);
                List<Task> tasks = this.taskManager.getTasks();
                for (Task task: tasks) {
                    String url = "https://nomads.ncep.noaa.gov/cgi-bin/filter_gfs_1p00.pl?";
                    url = url + ("file=" + fileName);
                    url = url + ("&lev_"+task.getLevel()+ "_mb=on");
                    if(task.getVariable().indexOf("|")> -1) {
                        StringBuffer variableBuffer = new StringBuffer("");
                        String[] variableArr = task.getVariable().split("\\|");
                        for (String variable: variableArr) {
                            variableBuffer.append("&").append(variable).append("=on");
                        }
                        url = url + variableBuffer.toString();
                    }else  {
                        url = url + ("&"+ task.getVariable()+ "=on");
                    }
                    url = url + ("&leftlon="+task.getSubregion().getLeftlon()+"&rightlon="+task.getSubregion().getRightlon()+"&toplat="+task.getSubregion().getToplat()+"&bottomlat="+task.getSubregion().getBottomlat());
                    url = url + ("&dir="+"/" + lastDayPath + "/" + lastHourPath);
                    FileUtil.download(url,localPath + File.separator,fileName+ "_" + task.getSubject() + "_" + task.getLevel());
                    logger.info(url);
                }
            }
            logger.info("最新数据目录["+ remotePath+ "]数据下载完毕。");
        }else {
            logger.info("本地目录["+ this.localDir+ "]未配置，请配置。");
        }

    }

    public String getLocalDir() {
        return localDir;
    }

    public void setLocalDir(String localDir) {
        this.localDir = localDir;
    }

}
