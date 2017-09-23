package com.lei.service;

import com.lei.dao.BaseDaoI;
import com.lei.entity.TUser;
import com.lei.model.GridModel;
import com.lei.model.User;

public interface UserServiceI extends BaseDaoI<TUser> {
	
	public void save(User user);

	public TUser login(User user);

	public GridModel getList(User user);
	
	public void remove(User user);

	public void add(User user);

	public void edit(User user);


}
