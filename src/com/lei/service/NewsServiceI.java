package com.lei.service;

import com.lei.dao.BaseDaoI;
import com.lei.entity.TNews;
import com.lei.model.GridModel;
import com.lei.model.News;

public interface NewsServiceI extends BaseDaoI<TNews>{

	public GridModel getList(News news);

	public void remove(News news);

	public void add(News news);

}
