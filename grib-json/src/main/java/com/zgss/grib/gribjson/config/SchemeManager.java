package com.zgss.grib.gribjson.config;

import com.zgss.grib.common.util.XsteamUtil;
import com.zgss.grib.gribjson.entity.Scheme;
import com.zgss.grib.gribjson.entity.Schemes;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class SchemeManager {
    private List<Scheme> schemes;

    @PostConstruct
    private void loadTask() {
        // 读取XML文件
        Resource resource = new ClassPathResource("schemes.xml");
        StringBuffer buffer = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
            String line = "";
            while ((line = br.readLine()) !=null) {
                buffer.append(line);
            }
            Schemes schemes = ((Schemes) XsteamUtil.toBean(Schemes.class,buffer.toString()));
            this.schemes = schemes.getSchemeList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if(br!=null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Scheme> getSchemes() {
        return schemes;
    }

    public void setSchemes(List<Scheme> schemes) {
        this.schemes = schemes;
    }
}
