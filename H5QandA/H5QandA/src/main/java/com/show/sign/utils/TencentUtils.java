package com.show.sign.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

import java.io.*;

/*
 * 腾讯云cos工具类*/
public class TencentUtils {

    static String bucketName = "readerh5-1251593276"; //桶的名称
    static COSCredentials cred = null;
    static ClientConfig clientConfig = null;
    static COSClient cosClient = null;

    static {
        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = "AKIDmNinhKhma8QRAdpkPoxoOhkX2HFwMVQ5";
        String secretKey = "QbaNt87pIVX2HGyPcYFgrlOkqfEVVFiu";
        cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-guangzhou");
        clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        cosClient = new COSClient(cred, clientConfig);
    }

    public static void main(String[] args) throws IOException {
        String filepath="D:\\test\\t.jpg";
        upload2cloud(filepath,"test.jpg");

   //     InputStream in = new FileInputStream(filepath);
    //    byte[] bytes = new byte[in.available()];
    //    upload2cloud(bytes,"ss");
//        deleteFileFromcloud("test");
    }

    public static void upload2cloud(String filepath,String fileName){
        // 指定要上传的文件
        File localFile = new File(filepath);
        // 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);

        String eTag = putObjectResult.getETag();
        System.out.println(eTag);

        //cosClient.shutdown();
    }

    public static void upload2cloud(byte[] bytes,String fileName){
        int length = bytes.length;
        // 获取文件流
        InputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectMetadata objectMetadata = new ObjectMetadata();
// 从输入流上传必须制定content length, 否则http客户端可能会缓存所有数据，存在内存OOM的情况
        objectMetadata.setContentLength(length);
// 默认下载时根据cos路径key的后缀返回响应的contenttype, 上传时设置contenttype会覆盖默认值

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, byteArrayInputStream, objectMetadata);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        String eTag = putObjectResult.getETag();
        System.out.println(eTag);
        //cosClient.shutdown();
    }

    public static void deleteFileFromcloud(String fileName){
        cosClient.deleteObject(bucketName, fileName);
       // cosClient.shutdown();
    }

}