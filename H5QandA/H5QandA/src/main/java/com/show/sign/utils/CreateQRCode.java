package com.show.sign.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class CreateQRCode {

    private static final int WIDTH = 450;
    private static final int HEIGHT = 450;


    
    public static String getTrdCodeUrl(String code,int flag,String rootpath) {

        String format = "png";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间

        String content = date +""+ code +""+ flag;

        //定义二维码参数
        HashMap hints = new HashMap();
        //编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //容错率
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);

        //获取项目路径
        String path = rootpath;
        //生成二维码
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode("/kaquan/enterVIPTicket?code="+code, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
            File dir=new File(path+"/QRcode/"+date+"/");
            if(!dir.exists()){
                dir.mkdirs();
            }
            Path file = new File(path+"/QRcode/"+date+"/"+content+".png").toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回图片地址
        return "http://dt1.fswrkj.com:8080/QRcode/"+date+"/"+content+".png";
    }
    

    public static void main(String[] args) {
       System.out.println(getTrdCodeUrl("123",1,"/root/apache-tomcat-8.5.33/webapps/hsticket"));
    }
}
