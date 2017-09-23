package com.lei.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.lei.action.base.BaseAction;
import com.lei.entity.TUser;
import com.lei.model.GridModel;
import com.lei.model.JsonModel;
import com.lei.model.SessionInfo;
import com.lei.model.User;
import com.lei.service.UserServiceI;
import com.lei.utils.IpUtil;
import com.opensymphony.xwork2.ModelDriven;

@Action(value = "userAction")
public class UserAction extends BaseAction implements ModelDriven<User>,ServletRequestAware{
	
	private Logger logger = Logger.getLogger(UserAction.class);
	private User user = new User();
	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	@Override
	public User getModel() {
		return user;
	}
	private UserServiceI userService;
	
	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	/**
	 * 注册
	 * @return
	 */
	public void reg() throws Exception{
		JsonModel j = new JsonModel();
		try {
			userService.save(user);
			
			j.setSuccess(true);
			j.setMsg("注册成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJSON(j);
	}
	/**
	 * 登录
	 */
	public void login() {
		JsonModel j = new JsonModel();
		TUser tUser = userService.login(user);
		if (tUser!=null) {
			
			SessionInfo info = new SessionInfo();
			info.setId(tUser.getId());
			info.setName(tUser.getNick());
			info.setIp("120.194.126.226");
			//System.out.println(IpUtil.getIpAddr(request));
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("sessionInfo", info);
			j.setSuccess(true);
			j.setMsg("登录成功！");
			logger.info(tUser.getNick()+"用户登录成功");
		}else {
			j.setMsg("登录失败！用户或密码错误");
		}
		super.writeJSON(j);
	}
	/**
	 * 得到全部用户
	 */
	public void getList() {
		GridModel g = userService.getList(user);
		super.writeJSON(g);
	}
	
	/**
	 * 后台添加用户
	 */
	public void add() {
		JsonModel j = new JsonModel();
		try {
			userService.add(user);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJSON(j);
	}
	
	/**
	 * 刪除用戶
	 */
	public void remove() {
		JsonModel j = new JsonModel();
		try {
			userService.remove(user);
			
			j.setSuccess(true);
			j.setMsg("刪除成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJSON(j);
	}
	/**
	 * 修改用户
	 */
	public void edit() {
		JsonModel j = new JsonModel();
		try {
			TUser tUser = new TUser();
			BeanUtils.copyProperties(user, tUser);
			tUser.setUpdateTime(new Date());
			userService.update(tUser);
			
			j.setSuccess(true);
			j.setMsg("修改成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJSON(j);
	}
}
