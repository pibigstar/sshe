package com.lei.action;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lei.action.base.BaseAction;
import com.lei.entity.TFilm;
import com.lei.model.Film;
import com.lei.model.GridModel;
import com.lei.model.JsonModel;
import com.lei.service.FilmServiceI;
import com.opensymphony.xwork2.ModelDriven;

@Action(value = "filmAction")
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
	 */
	public void add() {
		JsonModel j = new JsonModel();
		try {
			filmService.add(film);;
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
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
