package com.lei.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lei.dao.BaseDaoI;
import com.lei.dao.impl.BaseDaoImpl;
import com.lei.entity.TClassify;
import com.lei.model.GridModel;
import com.lei.service.ClassifyServiceI;

@Service("classifyService")
public class ClassifyServiceImpl extends BaseDaoImpl<TClassify> implements ClassifyServiceI {

	private BaseDaoI<TClassify> classifyDao;
	
	@Autowired
	public void setClassifyDao(BaseDaoI<TClassify> classifyDao) {
		this.classifyDao = classifyDao;
	}

	/**
	 * 返回所有分类
	 */
	@Override
	public GridModel getList(TClassify classify) {
		GridModel g = new GridModel();
		String hql = "from TClassify t";
		Map<String,Object> m = new HashMap<>();
		
		if (null!=classify.getText() && !"".equals(classify.getText().trim())) {
			hql += " where t.text like :text";
			m.put("text", "%%"+classify.getText()+"%%");
		}
		String totalHql = "select count(*)" + hql;
		g.setTotal(classifyDao.count(totalHql,m));
		List<TClassify> classifies = classifyDao.find(hql,m);
		
		g.setRows(classifies);
		
		return g;
	}


	@Override
	public void remove(String nids) {
		String[] ids = nids.split(",");
		String hql = "delete TClassify t where t.id in (";
		int length = ids.length;
		for (int i = 0; i < length; i++) {
			if (i>0) {
				hql += ",";
			}
			hql += "'"+ids[i]+"'";
		}
		hql += ")";
		classifyDao.executeHql(hql);
	}

	@Override
	public void add(TClassify classify) {
		classify.setId(UUID.randomUUID().toString());
		classify.setCreateTime(new Date());
		classify.setUpdateTime(new Date());
		classifyDao.save(classify);
		
	}
	
}
