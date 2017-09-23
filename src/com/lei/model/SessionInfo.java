package com.lei.model;

/**
 * session信息模型
 */
public class SessionInfo implements java.io.Serializable {

	private String id;// 用户ID
	private String name;// 用户登录名
	private String ip;// 用户IP

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
