package com.lei.service;

import java.util.List;

import com.lei.dao.BaseDaoI;
import com.lei.entity.TMenu;
import com.lei.model.Menu;

public interface MenuServiceI extends BaseDaoI<TMenu> {

	public List<Menu> getMenu(String id);

}
