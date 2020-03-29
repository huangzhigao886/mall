package com.mall.file.util;

import com.mall.file.bean.FdfsFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件管理工具类
 *
 * @Author: huangzhigao
 * @Date: 2020/3/29 22:43
 */

public class FastDFSUtil {

    static {
        //查找文件路径
        String filePath = new ClassPathResource("fdfs_client.conf").getPath();
        try {
            //获取tracker信息
            ClientGlobal.init(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }


    /**
     * 文件上传
     *
     * @param fdfsFile 封装文件的信息
     */
    public static String[] update(FdfsFile fdfsFile) throws IOException, MyException {
        NameValuePair[] nameValuePairs = new NameValuePair[1];
        nameValuePairs[0] = new NameValuePair("author", fdfsFile.getAuthor());
        StorageClient storageClient = getClient();
        //实现文件的上传
        String[] res = storageClient.upload_file(fdfsFile.getContent(), fdfsFile.getExt(), nameValuePairs);
        //res[0] 代表组，res[1]代表上传的文件名
        return res;
    }

    /**
     * 获取文件信息
     *
     * @param groupName 组
     * @param filePath  文件路径   /M00/00/00/AAA.JPG
     * @return
     */
    public static FileInfo getFileInfo(String groupName, String filePath) throws IOException, MyException {
        StorageClient client = getClient();
        FileInfo file_info = client.get_file_info(groupName, filePath);
        return file_info;
    }


    /**
     * 文件下载
     *
     * @param groupName 组
     * @param filePath  文件路径   /M00/00/00/AAA.JPG
     * @return
     */
    public static InputStream downloadFile(String groupName, String filePath) throws IOException, MyException {
        StorageClient client = getClient();
        byte[] bytes = client.download_file(groupName, filePath);
        return new ByteArrayInputStream(bytes);
    }

    /**
     * 删除文件
     *
     * @param groupName 组
     * @param filePath  文件路径   /M00/00/00/AAA.JPG
     * @return
     */
    public static void deleteFile(String groupName, String filePath) throws IOException, MyException {
        StorageClient client = getClient();
        client.delete_file(groupName, filePath);
    }


    /**
     * 获取storageClient
     *
     * @return
     */
    public static StorageClient getClient() throws IOException {
        //创建tracker的客户端
        TrackerClient trackerClient = new TrackerClient();
        //访问tracker服务器，获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取storage的连接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }


    public static void main(String[] args) throws IOException, MyException {
        InputStream in = downloadFile("group1", "M00/00/00/wKgByF6AuoKACDL9AAC-o5GaN74088.jpg");
        FileOutputStream out = new FileOutputStream("D://1.jpg");
        byte[] bytes = new byte[1024];
        while (in.read(bytes) != -1) {
            out.write(bytes);
        }
        in.close();
        out.close();
    }
}
