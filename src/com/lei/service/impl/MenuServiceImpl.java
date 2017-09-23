package com.lei.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.lei.dao.impl.BaseDaoImpl;
import com.lei.entity.TMenu;
import com.lei.model.Menu;
import com.lei.service.MenuServiceI;

@Service("menuService")
public class MenuServiceImpl extends BaseDaoImpl<TMenu> implements MenuServiceI {

	@Override
	public List<Menu> getMenu(String id) {
		List<Menu> menus = new ArrayList<>();
		String hql = "";
		if (id==null || "".equals(id)) {
			hql = "from TMenu as t where t.id='0'";
		}else {
			hql = "from TMenu as t where t.TMenu.id ='"+id+"'";
		}
		List<TMenu> tMenus = find(hql);
		
		for (TMenu tMenu : tMenus) {
			Menu m = new Menu();
			BeanUtils.copyProperties(tMenu, m);
			Set<TMenu> set = tMenu.getTMenus();
			if (set!=null && !set.isEmpty()) {
				//如果它下面有子节点，那么就让其的图标为文件夹状态
				m.setState("closed");
			}else {
				m.setState("open");
			}
			menus.add(m);
		}
		return menus;
	}

}
