package com.lei.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.lei.action.base.BaseAction;
import com.lei.model.Menu;
import com.lei.service.MenuServiceI;
import com.opensymphony.xwork2.ModelDriven;

@Action(value = "menuAction")
public class MenuAction extends BaseAction implements ModelDriven<Menu>{
	private Menu menu = new Menu();
	
	@Override
	public Menu getModel() {
		return menu;
	}
	
	private MenuServiceI menuService;

	public MenuServiceI getMenuService() {
		return menuService;
	}
	@Autowired
	public void setMenuService(MenuServiceI menuService) {
		this.menuService = menuService;
	}
	
	public void getMenu() {
		List<Menu> menus = menuService.getMenu(menu.getId());
		writeJSON(menus);
	}

}
