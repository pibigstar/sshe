package com.lei.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lei.action.base.BaseAction;
import com.lei.entity.TClassify;
import com.lei.entity.TNews;
import com.lei.model.GridModel;
import com.lei.model.JsonModel;
import com.lei.service.ClassifyServiceI;
import com.opensymphony.xwork2.ModelDriven;

@Action(value = "classifyAction")
public class ClassifyAction extends BaseAction implements ModelDriven<TClassify>  {
	private TClassify classify = new TClassify();
	@Override
	public TClassify getModel() {
		return classify;
	}
	private String ids;
	
	public void setIds(String ids) {
		this.ids = ids;
	}
	private ClassifyServiceI classifyService;
	@Autowired
	public void setClassifyService(ClassifyServiceI classifyService) {
		this.classifyService = classifyService;
	}
	
	/**
	 * 得到全部分类
	 */
	public void getList() {
		GridModel g = classifyService.getList(classify);
		super.writeJSON(g);
	}
	
	/**
	 * 删除分类
	 */
	public void remove() {
		JsonModel j = new JsonModel();
		try {
			classifyService.remove(ids);
			j.setSuccess(true);
			j.setMsg("刪除成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJSON(j);
	}
	
	/**
	 * 后台添加分类
	 */
	public void add() {
		JsonModel j = new JsonModel();
		try {
			classifyService.add(classify);;
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJSON(j);
	}
	
	/**
	 * 修改分类
	 */
	public void edit() {
		JsonModel j = new JsonModel();
		try {
			TClassify tClassify = new TClassify();
			BeanUtils.copyProperties(classify, tClassify);
			tClassify.setUpdateTime(new Date());
			classifyService.update(tClassify);
			
			j.setSuccess(true);
			j.setMsg("修改成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJSON(j);
	}

}
