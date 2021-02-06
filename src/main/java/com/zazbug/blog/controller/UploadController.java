package com.zazbug.blog.controller;

import com.zazbug.blog.entity.IpUtils;
import com.zazbug.blog.entity.Result;
import com.zazbug.blog.entity.StatusCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.InetAddress;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@RestController
public class UploadController {

	@Value("${web.upload-path}")
	private String uploadPath;

	@Value("${server.port}")
	private int port;

	@Value(("${web.ip}"))
	private String Ip;

	@PostMapping("/upload")
	public Result upload(@RequestParam("file")MultipartFile file){

		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
		String monthAndDay = simpleDateFormat.format(new Date());

		try {
			// 创建文件名
			String fileName = year + "/" + monthAndDay + "/" + UUID.randomUUID() + file.getOriginalFilename();
			String destFileName = uploadPath + fileName;
			// 创建文件夹目录
			File destFile = new File(destFileName);
			destFile.getParentFile().mkdirs();
			// 把上传的文件复制到希望的位置
			file.transferTo(destFile);
			String ipPortFilename = fileName;
			try {
				// InetAddress localHost = InetAddress.getLocalHost();
				// ipPortFilename = "http://" + localHost.getHostAddress() + ":" + port + "/image/" + fileName;
				ipPortFilename = "http://" + Ip + ":" + port + "/image/" + fileName;
			}catch (Exception e){
				e.printStackTrace();
			}
			return new Result(true,StatusCode.OK,"上传成功",ipPortFilename);

		}catch (Exception e){
			e.printStackTrace();
			return new Result(false, StatusCode.ERROR,"上传失败:"+e.getMessage());
		}

	}

}
