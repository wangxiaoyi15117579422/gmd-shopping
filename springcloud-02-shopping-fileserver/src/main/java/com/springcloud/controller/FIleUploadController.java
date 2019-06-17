package com.springcloud.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springcloud.common.UploadUtils;
import com.springcloud.vo.ResultValue;

@RestController
public class FIleUploadController {
	
	//从application.properties文件中获得指定键的值，并赋给相应的成员变量
	@Value("${web.user-path}")
	private String userPath;
	
	@Value("${web.goods-path}")
	private String goodsPath;
	
	@RequestMapping(value="/userUpload")
	//@RequestParam("userHphoto")中获取 的属性必须和HTML中上传文件的form表单中上传文件的控件的name属性一致
	public ResultValue userUpload(@RequestParam("userHphoto") MultipartFile file) {
		ResultValue rv = new ResultValue();
		
		//上传文件
		try {
			Map<String, Object> map = this.uploadFile(userPath, file);
			if (map != null && map.size() > 0) {
				rv.setCode(0);
				rv.setDataMap(map);
				return rv;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户头像上传失败！！");
		return rv;
	}
	
	@RequestMapping(value="/goodsUpload")
	//@RequestParam("goodPhoto")中获取 的属性必须和HTML中上传文件的form表单中上传文件的控件的name属性一致
	public ResultValue goodsUpload(@RequestParam("goodPhoto") MultipartFile file) {
		ResultValue rv = new ResultValue();
		
		//上传文件
		try {
			Map<String, Object> map = this.uploadFile(goodsPath, file);
			if (map != null && map.size() > 0) {
				rv.setCode(0);
				rv.setDataMap(map);
				return rv;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("商品图片上传失败！！");
		return rv;
	}
	
	/**
	 * 删除商品图片
	 * @param goodPhoto
	 * @return
	 */
	@RequestMapping(value="deleteGoodsPhoto")
	public ResultValue deleteGoodsPhoto(@RequestParam("goodPhoto") String goodPhoto) {
		ResultValue rv = new ResultValue();
		
		try {
			//从url中获得商品图片的名字（/最后一次出现的位置值之后就是名字）
			int indexOf = goodPhoto.lastIndexOf("/");
			if(indexOf != -1) {
				String fileName = goodPhoto.substring(indexOf + 1);
//				System.out.println(fileName);
				File file = new File(this.goodsPath + fileName);
				//判断文件或目录是否存在
//				if(file.exists()) {//文件是否存在
//					if(file.isFile()) {//是否是文件
//						file.delete();
//						
//						rv.setCode(0);
//						return rv;
//						
//					}
				file.delete();
				rv.setCode(0);
				return rv;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		return rv;
		
	}
	
	@RequestMapping(value="deleteUserHphoto")
	public ResultValue deleteUserHphoto(@RequestParam("userHphoto") String userHphoto) {
		ResultValue rv = new ResultValue();
		
		try {
			//从url中获得商品图片的名字（/最后一次出现的位置值之后就是名字）
			int indexOf = userHphoto.lastIndexOf("/");
			if(indexOf != -1) {
				String fileName = userHphoto.substring(indexOf + 1);
				File file = new File(this.userPath + fileName);
				file.delete();
				rv.setCode(0);
				return rv;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		return rv;
		
	}
	
	
	/**
	 * 上传文件
	 * @param path	上传文件的路径
	 * @param file	上传的文件
	 * @return	成功返回java.util.Map类型的实例，否则返回null
	 * @throws IOException 
	 */
	private Map<String,Object> uploadFile(String path,MultipartFile file) throws IOException{
		
		//获得新的文件名
		String fileName = UploadUtils.getFileName();
		
		//根据上传文件的文件名获得文件的拓展名
		String extendedName = UploadUtils.getExendedName(file.getOriginalFilename());
		
		//上传文件
		//1.将文件转换为字节类型的数组
		byte[] bytes = file.getBytes();
		//2.创建File类的对象，并设置我文件名上传路径及文件名
		File saveFile = new File(path + fileName + extendedName);
		//3.上传文件
		FileCopyUtils.copy(bytes, saveFile);
		
		Map<String,Object> map = new HashMap<>();
		map.put("fileName", fileName);
		map.put("extendedName", extendedName);
		return map;
		
	}

}
