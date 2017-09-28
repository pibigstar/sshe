package com.lei.service.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lei.dao.BaseDaoI;
import com.lei.dao.UserDaoI;
import com.lei.entity.TClassify;
import com.lei.entity.TMenu;
import com.lei.entity.TUser;
import com.lei.service.RepairServiceI;
import com.lei.utils.EncryptUtil;

@Service("repairService")
public class RepairServiceImpl implements RepairServiceI {

	private UserDaoI userDao;
	private BaseDaoI<TMenu> menuDao;
	private BaseDaoI<TClassify> classifyDao;
	
	
	@Autowired
	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}
	@Autowired
	public void setMenuDao(BaseDaoI<TMenu> menuDao) {
		this.menuDao = menuDao;
	}
	@Autowired
	public void setClassifyDao(BaseDaoI<TClassify> classifyDao) {
		this.classifyDao = classifyDao;
	}
	
	
	@Override
	public void repair() {
		
		repairUser();
		
		repairMenu();
		
		repairClassify();
		
	}
	//科幻，悬疑，爱情，剧情，战争，恐怖,武侠，冒险，动作，喜剧
	private void repairClassify() {
		TClassify  kh = new TClassify();
		kh.setId("kh");
		kh.setText("科幻");
		kh.setCreateTime(new Date());
		kh.setUpdateTime(new Date());
		classifyDao.saveOrUpdate(kh);
		
		TClassify  xy = new TClassify();
		xy.setId("xy");
		xy.setText("悬疑");
		xy.setCreateTime(new Date());
		xy.setUpdateTime(new Date());
		classifyDao.saveOrUpdate(xy);
		
		TClassify  aq = new TClassify();
		aq.setId("aq");
		aq.setText("爱情");
		aq.setCreateTime(new Date());
		aq.setUpdateTime(new Date());
		classifyDao.saveOrUpdate(aq);

		TClassify  jq = new TClassify();
		jq.setId("jq");
		jq.setText("剧情");
		jq.setCreateTime(new Date());
		jq.setUpdateTime(new Date());
		classifyDao.saveOrUpdate(jq);

		TClassify  zz = new TClassify();
		zz.setId("zz");
		zz.setText("战争");
		zz.setCreateTime(new Date());
		zz.setUpdateTime(new Date());
		classifyDao.saveOrUpdate(zz);

		TClassify  kb = new TClassify();
		kb.setId("kb");
		kb.setText("恐怖");
		kb.setCreateTime(new Date());
		kb.setUpdateTime(new Date());
		classifyDao.saveOrUpdate(kb);

		TClassify  wx = new TClassify();
		wx.setId("wx");
		wx.setText("武侠");
		wx.setCreateTime(new Date());
		wx.setUpdateTime(new Date());
		classifyDao.saveOrUpdate(wx);

		TClassify  mx = new TClassify();
		mx.setId("mx");
		mx.setText("冒险");
		mx.setCreateTime(new Date());
		mx.setUpdateTime(new Date());
		classifyDao.saveOrUpdate(mx);

		TClassify  dz = new TClassify();
		dz.setId("dz");
		dz.setText("动作");
		dz.setCreateTime(new Date());
		dz.setUpdateTime(new Date());
		classifyDao.saveOrUpdate(dz);

		TClassify  xj = new TClassify();
		xj.setId("xj");
		xj.setText("喜剧");
		xj.setCreateTime(new Date());
		xj.setUpdateTime(new Date());
		classifyDao.saveOrUpdate(xj);
		
	}
	private void repairMenu() {
		TMenu sy = new TMenu();
		sy.setId("0");
		sy.setText("首页");
		sy.setIconCls("icon-tip");
		menuDao.saveOrUpdate(sy);
		
		TMenu xtgl = new TMenu();
		xtgl.setId("xtgl");
		xtgl.setText("系统管理");
		xtgl.setTMenu(sy);
		menuDao.saveOrUpdate(xtgl);
		
		TMenu yhgl = new TMenu();
		yhgl.setId("yhgl");
		yhgl.setTMenu(xtgl);
		yhgl.setText("用户管理");
		yhgl.setUrl("admin/userManager.jsp");
		menuDao.saveOrUpdate(yhgl);
		
		TMenu nrgl = new TMenu();
		nrgl.setText("内容管理");
		nrgl.setTMenu(sy);
		nrgl.setId("nrgl");
		menuDao.saveOrUpdate(nrgl);
		
		TMenu ypgl = new TMenu();
		ypgl.setText("影片管理");
		ypgl.setTMenu(nrgl);
		ypgl.setId("ypgl");
		ypgl.setUrl("admin/filmManager.jsp");
		menuDao.saveOrUpdate(ypgl);
		
		TMenu ypfl = new TMenu();
		ypfl.setText("分类管理");
		ypfl.setTMenu(nrgl);
		ypfl.setId("ypfl");
		ypfl.setUrl("admin/classifyManager.jsp");
		menuDao.saveOrUpdate(ypfl);
		
		TMenu xwgl = new TMenu();
		xwgl.setId("xwgl");
		xwgl.setText("新闻管理");
		xwgl.setTMenu(xtgl);
		xwgl.setUrl("admin/newsManager.jsp");
		menuDao.saveOrUpdate(xwgl);
		
		TMenu cdgl = new TMenu();
		cdgl.setId("cdgl");
		cdgl.setText("菜单管理");
		cdgl.setTMenu(xtgl);
		cdgl.setUrl("admin/menuManager.jsp");
		//menuDao.saveOrUpdate(cdgl);
	}
	
	private void repairUser() {
		TUser tUser = new TUser();
		tUser.setId("0");
		tUser.setUsername("admin");
		tUser.setPassword(EncryptUtil.e("admin"));
		tUser.setCreateTime(new Date());
		tUser.setNick("admin");
		
		userDao.saveOrUpdate(tUser);
	}

}
