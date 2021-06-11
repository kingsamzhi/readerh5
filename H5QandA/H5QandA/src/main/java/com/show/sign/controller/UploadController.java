package com.show.sign.controller;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.show.sign.entity.WX;
import com.show.sign.utils.TencentUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/upload")
public class UploadController {





	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping("/uploadImage")
	public @ResponseBody Map<String,Object> addImage(@Param("file") MultipartFile file,HttpServletRequest request){
		String filenowname=null;//系统生成的名称
		boolean res=true;
		try {
			InputStream imp = file.getInputStream();
			byte[] bytes = new byte[imp.available()];
			String fileoriname=null;//原名称

			if(file != null){

				fileoriname = file.getOriginalFilename();//获取原名字
				String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
				String suffix = fileoriname.substring(fileoriname.lastIndexOf(".") + 1);

				filenowname = uuid+ "."+suffix;//UUID生成新的唯一名字+文件扩展名


				SimpleDateFormat sfp=new SimpleDateFormat("yyyyMMdd");
				String date=sfp.format(new Date());
				String real=request.getSession().getServletContext().getRealPath("/");

				String path=real+("uploaded/"+date);
				//创建文件夹
				File tempFile = new File(path, filenowname);
				if (!tempFile.getParentFile().exists()) {
					tempFile.getParentFile().mkdirs();
				}
				if (!tempFile.exists()) {
					tempFile.createNewFile();
				}
				file.transferTo(tempFile);
				String filepath=tempFile.getAbsolutePath();
				TencentUtils.upload2cloud(filepath,filenowname);
			}
			//TencentUtils.upload2cloud(bytes,filenowname);
		}catch(Exception ex){
			ex.printStackTrace();
			res=false;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,String> result=new HashMap<String,String>();
		if(res==true) {
			map.put("code", 0);//0表示成功，1失败
			map.put("msg", "上传成功");//提示消息
			result.put("src", WX.tencentURL + filenowname);//图片url
			result.put("title", filenowname);//图片名称，这个会显示在输入框里
			map.put("data",  result);
		}else{
			map.put("code",1);//0表示成功，1失败
			map.put("msg","上传失败");//提示消息
			result.put("src","");//图片url
			result.put("title","");//图片名称，这个会显示在输入框里
			map.put("data",result);
		}
		return map;
	}





	@CrossOrigin(origins = "*", maxAge = 3600)
	 @RequestMapping("/uploadExcel")
	    public @ResponseBody Map<String,Object> uploadExcel(@Param("file") MultipartFile file,HttpServletRequest request){
		 	Map<String,String> result=new HashMap<String,String>();
	        //1.保存图片到本地
	        String fileoriname=null;//原名称
	        String filenowname=null;//系统生成的名称
	        if(file != null){
	            fileoriname = file.getOriginalFilename();//获取原名字
	            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
	            String suffix = fileoriname.substring(fileoriname.lastIndexOf(".") + 1);
	            
	            filenowname = uuid+ "."+suffix;//UUID生成新的唯一名字+文件扩展名
	        }

	        try {
	        	 SimpleDateFormat sfp=new SimpleDateFormat("yyyyMMdd");
	        	 String date=sfp.format(new Date());
	        	 String real=request.getSession().getServletContext().getRealPath("/");
	        	 
	             String path=real+("uploaded/"+date);
	            //创建文件夹
	             File tempFile = new File(path, filenowname);
	             if (!tempFile.getParentFile().exists()) {
	                 tempFile.getParentFile().mkdirs();
	             }
	             if (!tempFile.exists()) {
	                 tempFile.createNewFile();
	             }
	             file.transferTo(tempFile);
	            // result.put("url", tempFile.getName());
	             

	             Map<String,Object> map = new HashMap<String,Object>();
	             map.put("code",0);//0表示成功，1失败

	             map.put("msg","上传成功");//提示消息
	             
	             result.put("src","uploaded/"+date+"/"+tempFile.getName());//图片url
				 result.put("src",tempFile.getAbsolutePath());//图片url
	             result.put("title",tempFile.getName());//图片名称，这个会显示在输入框里
	             
	             map.put("data",result);
	             return map;
	             
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	       
	        Map<String,Object> map = new HashMap<String,Object>();
            map.put("code",1);//0表示成功，1失败
            map.put("msg","上传失败");//提示消息            
            result.put("src","");//图片url
			//result.put("realpath","");//图片url
            result.put("title","");//图片名称，这个会显示在输入框里            
            map.put("data",result);
	        return map;
	    }

}
