package com.lei.service;

import com.lei.dao.BaseDaoI;
import com.lei.entity.TClassify;
import com.lei.model.GridModel;

public interface ClassifyServiceI extends BaseDaoI<TClassify>{
	
	public GridModel getList(TClassify classify);
	
	public void remove(String ids);

	public void add(TClassify classify);

}
