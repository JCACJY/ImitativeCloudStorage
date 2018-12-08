package cn.cloudstorage.qst.action;

import java.io.File;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cloudstorage.qst.domain.FileInfo;
import cn.cloudstorage.qst.service.ReadFileService;

import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")
@Component("showFilesAction")
public class ShowFilesAction extends ActionSupport{
	/**
	 * 取得当前目录下的所有子文件
	 * 客户端通过ajax请求，返回的是一个json
	 */
	@Resource(name="readFileServiceImpl")
	private ReadFileService  readFileService;
	private static final long serialVersionUID = 1L;
	private String currentPath;
	private List<FileInfo> fileSet;
	
	public String showFiles(){
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			String basepath= ServletActionContext.getServletContext().getRealPath("/upload");
			//String basepath=uploadpath+File.separator+homepath;				//D:\\tomcat\\cloudstorage\\upload
			//fileSet= new ArrayList<FileInfo>();
			String path=basepath+File.separator+getCurrentPath();				//D:\\tomcat\\cloudstorage\\upload\\10101928-18888107051 \\jdk\\jre7
			path=path.replace("/", File.separator);
			File file=new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			//拼接网络地址
			String fileURL = request.getScheme()+"://"+request.getServerName()
					   				+":"+request.getServerPort()+request.getContextPath()
					   				+"/upload/"+getCurrentPath().replace("\\", "/");
			fileSet= new ArrayList<FileInfo>(readFileService.getChildFiles(path,fileURL));
			Collections.sort(fileSet);
			//fileSet = readFileService.getChildFiles(path,fileURL);	
			return SUCCESS;
		} catch (Exception e) {
			addActionMessage("出错啦");
			return ERROR;
		}
	}
	
	@JSON(serialize=false)
	public String getCurrentPath() {return currentPath;}
	public void setCurrentPath(String currentPath) {this.currentPath = currentPath;}
	@JSON(serialize=true)
	public List<FileInfo> getFileSet() {return fileSet;}
}
