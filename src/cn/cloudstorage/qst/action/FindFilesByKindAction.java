package cn.cloudstorage.qst.action;

import java.io.File;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cloudstorage.qst.domain.SameKindFile;
import cn.cloudstorage.qst.domain.User;
import cn.cloudstorage.qst.serviceimpl.ShowFilesByKindService;

import com.opensymphony.xwork2.ActionSupport;

@Component("findFilesByKindAction")
@Scope("prototype")
public class FindFilesByKindAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	@Resource(name="showFilesByKindService")
	private ShowFilesByKindService sfbks;
	//客户端通过ajax传来文件类型参数
	private String fileType;
	private Set<SameKindFile> sameKindFiles;
	
	public String showFilesByKind(){
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession session=request.getSession();
			User user_online=(User)session.getAttribute("user_online");
			String homepath=ServletActionContext.getServletContext().getRealPath("/upload")+
							File.separator+user_online.getHomepath();
			//应用的URL
			String applicationURL = request.getScheme()+"://"+request.getServerName()
			   +":"+request.getServerPort()+request.getContextPath()+"/"+"upload"+"/";
			//取得相同类型的文件----json格式返回
			this.sameKindFiles=sfbks.showSameKindFile(fileType, homepath, applicationURL);
			return SUCCESS;
		} catch (Exception e) {
			this.sameKindFiles=null;
			return ERROR;
		}
	}
	//setter、getter
	@JSON(serialize=false)
	public String getFileType() {return fileType;}
	public void setFileType(String fileType) {this.fileType = fileType;}
	public Set<SameKindFile> getSameKindFiles(){return sameKindFiles;}
}
