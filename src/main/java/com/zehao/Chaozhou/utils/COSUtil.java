package com.zehao.Chaozhou.utils;

import com.google.gson.Gson;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.zehao.Chaozhou.common.GsonSingleton;
import com.zehao.Chaozhou.common.JsonResult;
import com.zehao.Chaozhou.common.config.COSConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

public class COSUtil {

    private static COSClient cosClient;
    private static PutObjectRequest putObjectRequest;

    private static Gson gson = GsonSingleton.getInstance();

    public static String uploadWithWait(MultipartFile file,Picture picture) throws IOException {
        JsonResult result = new JsonResult();
        result.setStatus(-1);
        String filename = upload(file,picture);
        try {
            cosClient.putObject(putObjectRequest);
            result.setStatus(0);
            result.setData(filename);
        }  catch (CosClientException e) {
            e.printStackTrace();
        }
        // 关闭客户端
        cosClient.shutdown();
        return gson.toJson(result);
    }

    public static String uploadWithoutWait(MultipartFile file,Picture picture) throws IOException {
        String filename = upload(file,picture);
        new Thread() {
            public void run() {
                try {
                    cosClient.putObject(putObjectRequest);
                }  catch (CosClientException e) {
                    e.printStackTrace();
                }
                // 关闭客户端
                cosClient.shutdown();
            }
        }.start();
        return filename;
    }

    //单图片上传
    public static String upload(MultipartFile file,Picture picture) throws IOException {
        //final JsonResult result = new JsonResult();
        //result.setStatus(-1);
        String key;
        String contentType = file.getContentType();
        InputStream inputStream = file.getInputStream();
        long size = file.getSize();
        if (size != 0) {
            // 初始化用户身份信息(secretId, secretKey)
            COSCredentials cred = new BasicCOSCredentials(COSConfig.SECRETID, COSConfig.SECRETKEY);
            // 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
            ClientConfig clientConfig = new ClientConfig(new Region(COSConfig.REGION));
            // 生成cos客户端
            cosClient = new COSClient(cred, clientConfig);
            // bucket名需包含appid
            String bucketName = COSConfig.BUCKETNAME;

            // 获取后缀名
            //String[] split = contentType.split("/");
            // 文件名
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            // 目标文件名
            key = "/" + picture.name() + "/" + uuid + ".jpg";

            ObjectMetadata objectMetadata = new ObjectMetadata();
            // 从输入流上传必须制定content length, 否则http客户端可能会缓存所有数据，存在内存OOM的情况
            objectMetadata.setContentLength(size);
            // 默认下载时根据cos路径key的后缀返回响应的contenttype, 上传时设置contenttype会覆盖默认值
            objectMetadata.setContentType(contentType);

            putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, objectMetadata);
            // 设置存储类型, 默认是标准(Standard), 低频(standard_ia), 近线(nearline)
            putObjectRequest.setStorageClass(StorageClass.Standard);

            return key.substring(1);
        }
        return "";
    }
}
