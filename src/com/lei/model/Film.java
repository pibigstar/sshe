package com.lei.model;

import java.io.File;
import java.util.Date;

public class Film {

	private String id;
	private String ids;
	private String name;
	private File upload;
	private String filmDescribe;
	private String classifyText;
	private String img;
	private String url;
	private Date createTime;
	private Date updateTime;
	private Date startTime;
	private String sort;
	private String order;
	
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilmDescribe() {
		return filmDescribe;
	}
	public void setFilmDescribe(String filmDescribe) {
		this.filmDescribe = filmDescribe;
	}
	public String getClassifyText() {
		return classifyText;
	}
	public void setClassifyText(String classifyText) {
		this.classifyText = classifyText;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
