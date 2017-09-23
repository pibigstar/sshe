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
import com.lei.entity.TFilm;
import com.lei.model.Film;
import com.lei.model.GridModel;
import com.lei.service.FilmServiceI;

@Service("filmService")
public class FilmServiceImpl extends BaseDaoImpl<TFilm> implements FilmServiceI {

	private BaseDaoI<TFilm> filmDao;
	
	@Autowired
	public void setFilmDao(BaseDaoI<TFilm> filmDao) {
		this.filmDao = filmDao;
	}

	@Override
	public GridModel getList(Film film) {
		GridModel g = new GridModel();
		String hql = "from TFilm t";
		Map<String,Object> m = null;
		
		if (null!=film.getName() && !"".equals(film.getName().trim())) {
			m = new HashMap<>();
			hql += " where t.name like :name";
			m.put("name", "%%"+film.getName()+"%%");
		}
		if (film.getSort()!=null && !film.getSort().trim().equals("")) {
			hql += " order by t.name "+film.getOrder();
		}
		String totalHql = "select count(*)" + hql;
		g.setTotal(filmDao.count(totalHql,m));
		List<TFilm> tFilms = filmDao.find(hql,m);
		List<Film> films = new ArrayList<>();
		for (TFilm tFilm : tFilms) {
			Film f = new Film();
			BeanUtils.copyProperties(tFilm, f);
			films.add(f);
		}
		g.setRows(films);
		
		return g;
	}

	@Override
	public void remove(Film film) {
		String[] ids = film.getIds().split(",");
		String hql = "delete TFilm t where t.id in (";
		int length = ids.length;
		for (int i = 0; i < length; i++) {
			if (i>0) {
				hql += ",";
			}
			hql += "'"+ids[i]+"'";
		}
		hql += ")";
		filmDao.executeHql(hql);
		
	}

	@Override
	public void add(Film film) {
		TFilm tFilm = new TFilm();
		BeanUtils.copyProperties(film, tFilm);
		tFilm.setCreateTime(new Date());
		tFilm.setUpdateTime(new Date());
		tFilm.setId(UUID.randomUUID().toString());
		
		/**还有下载链接 和 图片位置 没有写，回头再写
		 */
		filmDao.save(tFilm);
	}

}
