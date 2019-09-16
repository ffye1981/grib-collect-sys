package com.zgss.girb.gribdownload.service;

import com.zgss.girb.gribdownload.entity.Task;
import com.zgss.girb.gribdownload.entity.Tasks;
import com.zgss.grib.common.util.XsteamUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class TaskManager {
    private List<Task> tasks;

    @PostConstruct
    private void loadTask() {
        // 读取XML文件
        Resource resource = new ClassPathResource("task.xml");
        StringBuffer buffer = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
            String line = "";
            while ((line = br.readLine()) !=null) {
                buffer.append(line);
            }
            Tasks tasks = ((Tasks) XsteamUtil.toBean(Tasks.class,buffer.toString()));
            this.tasks = tasks.getTaskList();
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
