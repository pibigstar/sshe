package com.lei.model;

import java.io.Serializable;

public class JsonModel implements Serializable{
	
	private Boolean success = false;
	
	private String msg = "";
	
	private Object obj = null;

	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
	

}
