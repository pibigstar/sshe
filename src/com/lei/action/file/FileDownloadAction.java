package com.lei.action.file;

import java.io.InputStream;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FileDownloadAction extends ActionSupport
{
	private int number ;  
	  
    private String fileName;  
  
    public int getNumber() {  
        return number;  
    }  
  
    public void setNumber(int number) {  
        this.number = number;  
    }  
      
    public String getFileName() {  
        return fileName;  
    }  
  
    public void setFileName(String fileName) {  
        this.fileName = fileName;  
    }  
  
    //返回一个输入流，作为一个客户端来说是一个输入流，但对于服务器端是一个 输出流  
    public InputStream getDownloadFile() throws Exception  
    {  
        if(1 == number)  
        {  
           this.fileName = "Dream.jpg" ;  
           //获取资源路径  
           return ServletActionContext.getServletContext().getResourceAsStream("save/Dream.PNG") ;  
        }  
          
        else if(2 == number)  
        {  
            this.fileName = "jd2chm源码生成chm格式文档.rar" ;  
            //解解乱码  
            this.fileName = new String(this.fileName.getBytes("GBK"),"ISO-8859-1");  
            return ServletActionContext.getServletContext().getResourceAsStream("save/jd2chm源码生成chm格式文档.rar") ;  
        }  
        else  
           return null ;  
    }  
      
    @Override  
    public String execute() throws Exception {  
          
        return SUCCESS;  
    }  
  
}  