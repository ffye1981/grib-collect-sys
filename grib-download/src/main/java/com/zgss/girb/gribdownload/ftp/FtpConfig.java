package com.zgss.girb.gribdownload.ftp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ftp配置类
 */
@Component
public class FtpConfig {
    @Value("${ftp.username}")
    private String userName;

    @Value("${ftp.password}")
    private String passWord;

    @Value("${ftp.host}")
    private String ip;

    @Value("${ftp.port}")
    private String port;

    @Value("${ftp.root}")
    private String root;

    @Value("${ftp.maxTotal}")
    public int MaxTotal = 20;

    @Value("${ftp.minIdel}")
    public int MinIdel = 1;

    @Value("${ftp.maxIdle}")
    public int MaxIdle = 8;

    @Value("${ftp.maxWaitMillis}")
    public int MaxWaitMillis = 3000;

    public int getMaxTotal() {
        return MaxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        MaxTotal = maxTotal;
    }

    public int getMinIdel() {
        return MinIdel;
    }

    public void setMinIdel(int minIdel) {
        MinIdel = minIdel;
    }

    public int getMaxIdle() {
        return MaxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        MaxIdle = maxIdle;
    }

    public int getMaxWaitMillis() {
        return MaxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        MaxWaitMillis = maxWaitMillis;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}

