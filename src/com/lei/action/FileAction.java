package com.lei.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.lei.model.Film;
import com.lei.model.JsonModel;
import com.lei.service.FilmServiceI;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FileAction extends ActionSupport implements ModelDriven<Film>{
	private Film film = new Film();
	@Override
	public Film getModel() {
		return film;
	}
	
	private FilmServiceI filmService;
	@Autowired
	public void setFilmService(FilmServiceI filmService) {
		this.filmService = filmService;
	}
	
    public void add() throws Exception {  
    	JsonModel j = new JsonModel();
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			String fileName = UUID.randomUUID().toString().replace("-", "") +".jpg";
			String savePath = "D:\\upload\\"+fileName;
			
			fos = new FileOutputStream(savePath);
			
			fis = new FileInputStream(film.getUpload());
			byte [] b = new byte[1024];
			int len = 0;
			while ((len=fis.read(b))!=-1) {
				fos.write(b,0,len);
			}
			String imgPath = "http://localhost:8080/upload/"+fileName;
			film.setImg(imgPath);
			film.setUrl(imgPath);
			
			filmService.add(film);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}finally {
			fis.close();
			fos.close();
		}
		String json = JSON.toJSONStringWithDateFormat(j,"yyyy-MM-dd HH:mm:ss");
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().write(json);
    }  
}
