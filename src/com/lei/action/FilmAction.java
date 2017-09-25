package com.lei.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;
import com.lei.action.base.BaseAction;
import com.lei.model.Film;
import com.lei.model.GridModel;
import com.lei.model.JsonModel;
import com.lei.service.FilmServiceI;
import com.opensymphony.xwork2.ModelDriven;

@Action(value = "filmAction")
@InterceptorRefs(value = { @InterceptorRef("fileUploadStack") })  
public class FilmAction extends BaseAction implements ModelDriven<Film> {
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
	
	
	
	/**
	 * 得到全部电影
	 */
	public void getList() {
		GridModel g = filmService.getList(film);
		super.writeJSON(g);
	}
	
	/**
	 * 后台添加电影
	 * @throws IOException 
	 */
	public void add() throws IOException {
		JsonModel j = new JsonModel();
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			String fileName = UUID.randomUUID().toString().replace("-", "") +".jpg";
			String savePath = "D:\\upload\\"+fileName;
		
			fos = new FileOutputStream(savePath);
			
			fis = new FileInputStream(film.getFile());
			byte [] b = new byte[1024];
			int len = 0;
			while ((len=fis.read(b))!=-1) {
				fos.write(b,0,len);
			}
			film.setImg(savePath);
			filmService.add(film);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}finally {
			fis.close();
			fos.close();
		}
		super.writeJSON(j);
	}
	/**
	 * 删除电影
	 */
	public void remove() {
		JsonModel j = new JsonModel();
		try {
			filmService.remove(film);
			j.setSuccess(true);
			j.setMsg("刪除成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJSON(j);
	}
	

}
