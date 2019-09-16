package com.zgss.girb.gribdownload.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 连接工厂
 */
@Component
public class FtpClientFactory implements PooledObjectFactory<FTPClient> {
    @Autowired
    FtpConfig ftpConfig;

    /**
     * 创建连接池
     * @return
     * @throws Exception
     */
    @Override
    public PooledObject<FTPClient> makeObject() throws Exception {
        //创建客户端实例
        FTPClient ftpClient = new FTPClient();
        return new DefaultPooledObject<FTPClient>(ftpClient);
    }

    /**
     * 销毁连接（当连接池空闲数量达到上限时，调用此方法销毁连接）
     * @param pooledObject
     * @throws Exception
     */
    @Override
    public void destroyObject(PooledObject<FTPClient> pooledObject) throws Exception {
        FTPClient ftpClient = pooledObject.getObject();
        try {
            ftpClient.logout();
            if(ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        }catch (IOException e){
            throw new RuntimeException("Could not disconnect from server.",e);
        }

    }

    /**
     * 连接状态检查
     * @param pooledObject
     * @return
     */
    @Override
    public boolean validateObject(PooledObject<FTPClient> pooledObject) {
        FTPClient ftpClient = pooledObject.getObject();
        try{
            return ftpClient.sendNoOp();
        }catch (IOException e) {
            return false;
        }
    }

    /**
     * 初始化连接
     * @param pooledObject
     * @throws Exception
     */
    @Override
    public void activateObject(PooledObject<FTPClient> pooledObject) throws Exception {
        FTPClient ftpClient = pooledObject.getObject();
        String portStr = ftpConfig.getPort();
        try {
            int port = Integer.parseInt(portStr);
            ftpClient.connect(ftpConfig.getIp(),port);
        }catch (NumberFormatException e){
            ftpClient.connect(ftpConfig.getIp());
        }
        if(ftpConfig.getUserName()!=null &&
                ftpConfig.getPassWord() != null &&
                !ftpConfig.getUserName().isEmpty() &&
                !ftpConfig.getPassWord().isEmpty()) {
            ftpClient.login(ftpConfig.getUserName(),ftpConfig.getPassWord());
        }
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.setBufferSize(1024);
        //通知server开通一个端口传输数据
        ftpClient.enterLocalPassiveMode();
        // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
        String LOCAL_CHARSET = "GBK";
        if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
            LOCAL_CHARSET = "UTF-8";
        }
        ftpClient.setControlEncoding(LOCAL_CHARSET);
    }

    /**
     * 钝化连接
     * @param pooledObject
     * @throws Exception
     */
    @Override
    public void passivateObject(PooledObject<FTPClient> pooledObject) throws Exception {
        FTPClient ftpClient = pooledObject.getObject();
        try {
            ftpClient.changeWorkingDirectory(ftpConfig.getRoot());
            ftpClient.logout();
            if(ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        }catch (IOException e) {
            throw new RuntimeException("Could not disconnect from server.", e);
        }
    }
}
