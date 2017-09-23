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
import com.lei.dao.BaseDaoI;
import com.lei.dao.impl.BaseDaoImpl;
import com.lei.entity.TNews;
import com.lei.model.GridModel;
import com.lei.model.News;
import com.lei.service.NewsServiceI;

@Service("newsService")
public class NewsServiceImpl extends BaseDaoImpl<TNews> implements NewsServiceI {

	private BaseDaoI<TNews> newsDao;
	@Autowired
	public void setNewsDao(BaseDaoI<TNews> newsDao) {
		this.newsDao = newsDao;
	}

	@Override
	public GridModel getList(News news) {
		GridModel g = new GridModel();
		String hql = "from TNews t";
		Map<String,Object> m = null;
		
		if (null!=news.getTitle() && !"".equals(news.getTitle().trim())) {
			m = new HashMap<>();
			hql += " where t.title like :title";
			m.put("title", "%%"+news.getTitle()+"%%");
		}
		if (news.getSort()!=null && !news.getSort().trim().equals("")) {
			hql += " order by t.title "+news.getOrder();
		}
		
		String totalHql = "select count(*)" + hql;
		
		g.setTotal(newsDao.count(totalHql,m));
		
		List<TNews> tNews = newsDao.find(hql,m,news.getPage(),news.getRows());
		
		List<News> newes = new ArrayList<>();
		for (TNews tNew : tNews) {
			News n = new News();
			BeanUtils.copyProperties(tNew, n);
			newes.add(n);
		}
		g.setRows(newes);
		return g;
	}

	@Override
	public void remove(News news) {
		String[] ids = news.getIds().split(",");
		String hql = "delete TNews t where t.id in (";
		int length = ids.length;
		for (int i = 0; i < length; i++) {
			if (i>0) {
				hql += ",";
			}
			hql += "'"+ids[i]+"'";
		}
		hql += ")";
		newsDao.executeHql(hql);
	}

	@Override
	public void add(News news) {
		TNews tNews = new TNews();
		BeanUtils.copyProperties(news, tNews);
		tNews.setCreateTime(new Date());
		tNews.setUpdateTime(new Date());
		tNews.setId(UUID.randomUUID().toString());
		newsDao.save(tNews);
	}
}
