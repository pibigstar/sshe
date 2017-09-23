package com.lei.service;

import com.lei.dao.BaseDaoI;
import com.lei.entity.TFilm;
import com.lei.model.Film;
import com.lei.model.GridModel;

public interface FilmServiceI extends BaseDaoI<TFilm> {

	public GridModel getList(Film film);
	
	public void remove(Film film);

	public void add(Film film);

}
