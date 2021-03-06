package com.lei.entity;
// Generated 2017-9-19 9:44:35 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * TUser generated by hbm2java
 */
public class TUser implements java.io.Serializable {

	private String id;
	private String username;
	private String password;
	private String nick;
	private Date createTime;
	private Date updateTime;
	private String deleteFlag;

	public TUser() {
	}

	public TUser(String id, String username, String password, String nick) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nick = nick;
	}

	public TUser(String id, String username, String password, String nick, Date createTime, Date updateTime,
			String deleteFlag) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nick = nick;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.deleteFlag = deleteFlag;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}
