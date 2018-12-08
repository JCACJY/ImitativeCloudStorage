package cn.cloudstorage.qst.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component("downloadFileAction")
@Scope("prototype")
public class DownloadFileAction extends ActionSupport{
	/**
	 * 文件下载
	 * 从客户端接收一个文件路径参数filePath
	 */
	private static final long serialVersionUID = 1L;
	private String filePath;
	private String fileName;
	private InputStream fileInputStream;
	
	public String download(){
		try {
			File file=new File(filePath);				//文件在服务端路径
			if(!file.exists()){							//判断文件在服务端是否存在
				addActionMessage("文件不存在");
				return NONE;
			}
			this.fileName=file.getName();				//文件名称---传递到客户端	
			fileInputStream=new BufferedInputStream(new FileInputStream(file));
			addActionMessage("下载成功");
			return SUCCESS;
		} catch (Exception e) {
			addActionMessage("下载文件出错");
			return ERROR;
		}
	}
	//setter、getter
	public String getFilePath() {return filePath;}
	public void setFilePath(String filePath) throws Exception {
		this.filePath = new String(filePath.getBytes("ISO-8859-1"), "UTF-8");
	}
	@JSON(serialize=false)
	public String getFileName() throws Exception {
		return new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
	}
	public void setFileName(String fileName) {this.fileName = fileName;}
	@JSON(serialize=false)
	public InputStream getFileInputStream() {return fileInputStream;}
	public void setFileInputStream(InputStream fileInputStream) {this.fileInputStream = fileInputStream;}
}
