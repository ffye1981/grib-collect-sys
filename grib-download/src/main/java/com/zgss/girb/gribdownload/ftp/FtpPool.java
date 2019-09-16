package com.zgss.girb.gribdownload.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 1、可以从池中获取空闲连接
 * 2、可以归还连接到池中
 * 3、当池中空闲连接不足时，可以创建连接
 */
@Component
public class FtpPool {

    @Autowired
    FtpConfig ftpConfig;

    FtpClientFactory factory;
    private final GenericObjectPool<FTPClient> internalPool;

    /**
     * 初始化连接池
     * @param factory
     */
    public FtpPool(@Autowired FtpClientFactory factory) {
        this.factory = factory;
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
//        poolConfig.setMaxTotal(20);
//        poolConfig.setMinIdle(1);
//        poolConfig.setMaxIdle(8);
//        poolConfig.setMaxWaitMillis(3000);
        this.internalPool = new GenericObjectPool<FTPClient>(factory, poolConfig);

    }

    @PostConstruct
    private void initConfig() {
        this.internalPool.setMaxTotal(ftpConfig.getMaxTotal());
        this.internalPool.setMinIdle(ftpConfig.getMinIdel());
        this.internalPool.setMaxIdle(ftpConfig.getMaxIdle());
        this.internalPool.setMaxWaitMillis(ftpConfig.getMaxWaitMillis());
    }


    /**
     * 从池中获取连接
     * @return
     */
    public FTPClient getFtpClient(){
        try{
            FTPClient ftpClient =  this.internalPool.borrowObject();
            ftpClient.changeWorkingDirectory(ftpConfig.getRoot());
            return ftpClient;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将连接归还池中
     * @param ftpClient
     */
    public void returnFtpClient(FTPClient ftpClient) {
        try{
            this.internalPool.returnObject(ftpClient);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 销毁连接池
     */
    public void destory() {
        try{
            this.internalPool.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
