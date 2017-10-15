package com.springboot.server.pictureserver.controller;

import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.server.pictureserver.model.ResultVo;
import com.springboot.server.pictureserver.model.SUser;
import com.springboot.server.pictureserver.service.UploadImagesService;

@Controller
public class UploadImagesController {

	/**
	 * 用户管理 -> 业务层
	 */
	 @Autowired
	 private UploadImagesService uploadImagesService;
	  
	 /**
	 * 文件上传根目录(在Spring的application.yml的配置文件中配置):<br>
	 * web:
	 *  upload-path: （jar包所在目录）/resources/static/
	 */
	 @Value("${image.upload-path}")
	 private String webUploadPath;
	  
	 /**
	 * ResultVo是一个对象，包含：
	 * private int errorCode;
	 * private String errorMsg;
	 * private Integer total;
	 * private Object data;
	 */
	 
	 /**
	 * 基于用户标识的头像上传
	 * @param file 图片
	 * @param userId 用户标识
	 * @return
	 */
//	 @PostMapping(value = "/fileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	 @PostMapping(value = "/fileUpload")
	 @ResponseBody
	 public ResultVo fileUpload(@RequestParam("uploadFile") MultipartFile file, @RequestParam("userId") Integer userId) {
		 System.out.println("kaishi ");
		 ResultVo resultVo = new ResultVo();
		 //判断前端传过来的文件是否为空
		 if (!file.isEmpty()) {
			 //判断文件类型是否是图片格式
			 if (file.getContentType().contains("image")) {
				 try {
					 //temp=images/upload/	
					 String temp = "images" + File.separator + "upload" + File.separator;
					 // 获取图片的文件名
					 String fileName = file.getOriginalFilename();
					 // 获取图片的扩展名
					 String extensionName = StringUtils.substringAfter(fileName, ".");
					 // 新的图片文件名 = 获取时间戳+"."图片扩展名
					 String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
					 // 数据库保存的目录
					//datdDirectory=images/upload/{userId}/
					 String datdDirectory = temp.concat(String.valueOf(userId)).concat(File.separator);
					 // 文件路径
					 String filePath = webUploadPath.concat(datdDirectory);
	 
					 File dest = new File(filePath, newFileName);
					 if (!dest.getParentFile().exists()) {
						 dest.getParentFile().mkdirs();
					 }
					 // 判断是否有旧头像，如果有就先删除旧头像，再上传
					 SUser userInfo = uploadImagesService.findUserInfo(userId.toString());
					 if (StringUtils.isNotBlank(userInfo.getUserHead())) {
						 String oldFilePath = webUploadPath.concat(userInfo.getUserHead());
						 File oldFile = new File(oldFilePath);
						 if (oldFile.exists()) {
							 oldFile.delete();
						 }
					 }
					 // 上传到指定目录
					 file.transferTo(dest);
	 
					 // 将图片流转换进行BASE64加码
					 //BASE64Encoder encoder = new BASE64Encoder();
					 //String data = encoder.encode(file.getBytes());
	 
					 // 将反斜杠转换为正斜杠
					 String data = datdDirectory.replaceAll("\\\\", "/") + newFileName;
					 Map<String, Object> resultMap = new HashMap<>();
					 resultMap.put("file", data);
					 resultVo.setData(resultMap);
					 resultVo.setError(1, "上传成功!");
				 } catch (IOException e) {
					 resultVo.setError(0, "上传失败!");
				 }
			 } else {
				 resultVo.setError(0, "上传的文件不是图片类型，请重新上传!");
			 }
			 return resultVo;
		 } else {
			 resultVo.setError(0, "上传失败，请选择要上传的图片!");
			 return resultVo;
		 }
	 }
	 
	 @RequestMapping(value="/index",method=RequestMethod.GET)
//	 @ResponseBody
	 public String indexJsp(){
		 System.out.println("访问了");
		 return "index";
	 }
}
