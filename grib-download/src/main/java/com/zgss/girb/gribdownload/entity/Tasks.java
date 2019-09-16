package com.zgss.girb.gribdownload.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("tasks")
public class Tasks {
    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @XStreamImplicit(itemFieldName="task")
    private List<Task> taskList;
}
