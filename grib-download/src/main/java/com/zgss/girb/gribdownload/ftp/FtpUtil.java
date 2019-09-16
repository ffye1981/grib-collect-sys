package com.zgss.girb.gribdownload.ftp;

import com.zgss.girb.gribdownload.service.impl.GribServiceImpl;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Service
public class FtpUtil {
    private Logger logger = LoggerFactory.getLogger(FtpUtil.class);

    @Autowired
    FtpPool pool;

    @Autowired
    FtpConfig ftpConfig;

    // 判断ftp服务器文件是否存在
    public boolean existFile(String path) {
        FTPClient ftpClient = null;
        boolean flag = false;
        try {
            ftpClient = pool.getFtpClient();
            return ftpClient.changeWorkingDirectory(path);
        }catch(IOException e){
        }finally {
            this.pool.returnFtpClient(ftpClient);
        }
        return flag;
    }

    /**
     * 获取最新的日期目录
     * @param path
     * @return
     */
    public String getLastDaySub(String path) {
        String dayPath = "";
        FTPClient ftpClient = null;
        List<FTPFile> list = new ArrayList<>();
        try {
            ftpClient = pool.getFtpClient();
            FTPFile[] files = ftpClient.listDirectories(path);
            for (FTPFile file: files) {
                if(file.getName().startsWith("gfs.")) {
                    list.add(file);
                }
            }
            Collections.sort(list, new Comparator<FTPFile>() {
                public int compare(FTPFile f1, FTPFile f2) {
                    return f2.getTimestamp().compareTo(f1.getTimestamp());
                }
            });
            if(!list.isEmpty()) {
                dayPath = list.get(0).getName();
            }
            return dayPath;
        }catch(IOException e){
        }finally {
            this.pool.returnFtpClient(ftpClient);
        }
        return  dayPath;
    }

    /**
     * 获取最新的小时目录
     * @param path
     * @return
     */
    public String getLastHourSub(String path) {
        String dayPath = "";
        FTPClient ftpClient = null;
        try {
            ftpClient = pool.getFtpClient();
            FTPFile[] files = ftpClient.listDirectories(path);
            List<FTPFile> list = Arrays.asList(files);
            Collections.sort(list, new Comparator<FTPFile>() {
                public int compare(FTPFile f1, FTPFile f2) {
                    return f2.getTimestamp().compareTo(f1.getTimestamp());
                }
            });
            if(!list.isEmpty()) {
                dayPath = list.get(0).getName();
            }
            return dayPath;
        }catch(IOException e){
        }finally {
            this.pool.returnFtpClient(ftpClient);
        }
        return  dayPath;
    }

    /**
     * 获取指定前缀文件个数
     * @param remotePath
     * @param prefix
     * @return
     */
    public int cntFiles(String remotePath,String prefix,String postfix){
        FTPClient ftpClient = null;
        int cnt = 0;
        try {
            ftpClient = pool.getFtpClient();
            cnt = ftpClient.listFiles(remotePath, new FTPFileFilter() {
                @Override
                public boolean accept(FTPFile file) {
                    if (file.getName().startsWith(prefix) && (!file.getName().endsWith(postfix))) return true ;
                    return false ;
                }}).length;
        }catch(IOException e){
        }finally {
            this.pool.returnFtpClient(ftpClient);
        }
        return cnt;
    };

    /**
     * * 下载文件 *
     *
     * @param remotePath FTP服务器文件目录 *
     * @param fileNames 文件名称 *
     * @param localPath 下载后的文件路径 *
     * @return
     */
    public boolean downloadFile(String remotePath, List<String> fileNames, String localPath) {
        boolean flag = false;
        FTPClient ftpClient = null;
        try {
            logger.info("[批量下载文件开始...]");
            ftpClient = pool.getFtpClient();
            // 切换FTP目录

            if(!ftpClient.changeWorkingDirectory(remotePath)) {
                System.out.println("目录：" + remotePath + "不存在");
                return false;
            }
            for (String fileName: fileNames) {
                long start = Calendar.getInstance().getTimeInMillis();
                boolean dFlag = this.downloadFile(ftpClient,fileName,localPath);
                long delay = (Calendar.getInstance().getTimeInMillis() - start);
                System.out.println("\t下载文件" +fileName+ (dFlag? "成功":"失败") + ",耗时：" + delay + "毫秒");
            }
            flag = true;
            logger.info("[批量下载文件结束...]");
        } catch (Exception e) {
            System.out.println("批量下载文件失败");
            e.printStackTrace();
        } finally {
            this.pool.returnFtpClient(ftpClient);
        }
        return flag;
    }

    public boolean downloadFile(FTPClient ftpClient,String fileName, String localPath) {
        boolean flag = false;
        OutputStream os = null;
        FTPFile[] ftpFiles = new FTPFile[0];
        try {
            ftpFiles = ftpClient.listFiles();
            for (FTPFile file : ftpFiles) {
                if (fileName.equalsIgnoreCase(file.getName())) {
                    File localFile = new File(localPath + "/" + file.getName());
                    os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                }
            }
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
