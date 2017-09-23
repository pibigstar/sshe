package com.lei.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lei.dao.UserDaoI;
import com.lei.dao.impl.BaseDaoImpl;
import com.lei.entity.TUser;
import com.lei.model.GridModel;
import com.lei.model.User;
import com.lei.service.UserServiceI;
import com.lei.utils.EncryptUtil;

@Service("userService")
public class UserServiceImpl extends BaseDaoImpl<TUser> implements UserServiceI{
	
	private UserDaoI userDao;
	
	public UserDaoI getUserDao() {
		return userDao;
	}
	@Autowired
	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}

	/**
	 * 注册
	 */
	@Override
	public void save(User user) {
		TUser tUser = new TUser();
		BeanUtils.copyProperties(user, tUser,"password");
		tUser.setPassword(EncryptUtil.e(user.getPassword()));
		tUser.setId(UUID.randomUUID().toString());
		tUser.setCreateTime(new Date());
		tUser.setDeleteFlag("0");
		userDao.save(tUser);
		
	}
	/**
	 * 登录
	 */
	@Override
	public TUser login(User user) {
//		String hql = "from TUser t where t.username='"+user.getPassword()+"' and password='"+EncryptUtil.e(user.getPassword())+"'";
//		TUser tUser = userDao.get(hql);
		
		String hql = "from TUser as t where t.username=:username and t.password=:password";
		Map<String, Object> params = new HashMap<>();
		params.put("username", user.getUsername());
		params.put("password", EncryptUtil.e(user.getPassword()));
		TUser tUser = userDao.get(hql, params);
		return tUser;
	}
	/**
	 * 返回所有用户
	 */
	@Override
	public GridModel getList(User user) {
		GridModel g = new GridModel();
		String hql = "from TUser t";
		Map<String,Object> m = new HashMap<>();
		
		if (null!=user.getUsername() && !"".equals(user.getUsername().trim())) {
			hql += " where t.username like :username";
			m.put("username", "%%"+user.getUsername()+"%%");
		}
		if (user.getSort()!=null && !user.getSort().trim().equals("")) {
			hql += " order by t.username "+user.getOrder();
		}
		
		String totalHql = "select count(*)" + hql;
		
		g.setTotal(userDao.count(totalHql,m));
		
		
		List<TUser> tUsers = userDao.find(hql,m,user.getPage(),user.getRows());
		List<User> users = new ArrayList<>();
		for (TUser tuser : tUsers) {
			User u = new User();
			BeanUtils.copyProperties(tuser, u);
			users.add(u);
		}
		g.setRows(users);
		
		return g;
	}
	
	@Override
	public void remove(User user) {
		String[] ids = user.getIds().split(",");
		String hql = "delete TUser t where t.id in (";
		int length = ids.length;
		for (int i = 0; i < length; i++) {
			if (i>0) {
				hql += ",";
			}
			hql += "'"+ids[i]+"'";
		}
		hql += ")";
		userDao.executeHql(hql);
	}
	@Override
	public void add(User user) {
		TUser tUser = new TUser();
		BeanUtils.copyProperties(user, tUser);
		tUser.setId(UUID.randomUUID().toString());
		tUser.setPassword(EncryptUtil.e(user.getPassword()));
		tUser.setCreateTime(new Date());
		tUser.setUpdateTime(new Date());
		userDao.save(tUser);
	}
	@Override
	public void edit(User user) {
		
	}

}
