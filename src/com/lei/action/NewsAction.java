package com.lei.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lei.action.base.BaseAction;
import com.lei.entity.TNews;
import com.lei.entity.TUser;
import com.lei.model.GridModel;
import com.lei.model.JsonModel;
import com.lei.model.News;
import com.lei.service.NewsServiceI;
import com.opensymphony.xwork2.ModelDriven;

@Action(value = "newsAction")
public class NewsAction extends BaseAction implements ModelDriven<News>{
	private News news = new News();
	@Override
	public News getModel() {
		return news;
	}
	
	private NewsServiceI newsService;
	@Autowired
	public void setNewsService(NewsServiceI newsService) {
		this.newsService = newsService;
	}
	
	/**
	 * 得到全部新闻
	 */
	public void getList() {
		GridModel g = newsService.getList(news);
		super.writeJSON(g);
	}
	/**
	 * 添加新闻
	 */
	public void add() {
		JsonModel j = new JsonModel();
		try {
			newsService.add(news);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJSON(j);
	}
	
	/**
	 * 删除新闻
	 */
	public void remove() {
		JsonModel j = new JsonModel();
		try {
			newsService.remove(news);
			j.setSuccess(true);
			j.setMsg("刪除成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJSON(j);
	}
	
	/**
	 * 修改新闻
	 */
	public void edit() {
		JsonModel j = new JsonModel();
		try {
			TNews tNews = new TNews();
			BeanUtils.copyProperties(news, tNews);
			tNews.setUpdateTime(new Date());
			newsService.update(tNews);
			
			j.setSuccess(true);
			j.setMsg("修改成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJSON(j);
	}
	

}
