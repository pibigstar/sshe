package com.lei.action.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class FileUploadAction extends ActionSupport {
	
	private String tilte;//文件标题
	private File upload;//上传的文件对象
	private String uploadName;//上传文件名
	private String savePath;//获取xml文件中配置的文件保存路径
	
	public String getTilte() {
		return tilte;
	}

	public void setTilte(String tilte) {
		this.tilte = tilte;
	}

	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadName() {
		return uploadName;
	}

	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}

	public String getSavePath() {
		return ServletActionContext.getServletContext().getRealPath("/save");
	}

	public void setSavePath(String value) {
		this.savePath = value;
	}

	@Override
	public String execute() throws Exception {
		
		String path = ServletActionContext.getServletContext().getRealPath("/save");
		//D:\struts2\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\ssh\save
		System.out.println(path);
		
		String fileName = UUID.randomUUID().toString().replace("-", "") +".jpg";
		FileOutputStream fos = new FileOutputStream(path+"/"+fileName);
		
		FileInputStream fis = new FileInputStream(getUpload());
		byte [] b = new byte[1024];
		int len = 0;
		while ((len=fis.read(b))!=-1) {
			fos.write(b,0,len);
		}

		fis.close();
		fos.close();
		return SUCCESS;
	}

}
