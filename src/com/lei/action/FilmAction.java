package com.lei.action;



import java.util.Date;

import org.apache.log4j.Logger;
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
	private Logger logger = Logger.getLogger(FilmAction.class);
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
	 * 删除电影
	 */
	public void remove() {
		JsonModel j = new JsonModel();
		logger.info(film.getIds());
		try {
			filmService.remove(film);
			j.setSuccess(true);
			j.setMsg("刪除成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJSON(j);
	}
	
	/**
	 * 修改d电影
	 */
	public void edit() {
		JsonModel j = new JsonModel();
		try {
			TFilm tFilm = new TFilm();
			BeanUtils.copyProperties(film, tFilm);
			tFilm.setUpdateTime(new Date());
			filmService.update(tFilm);
			
			j.setSuccess(true);
			j.setMsg("修改成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJSON(j);
	}
	

}
