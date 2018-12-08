package cn.cloudstorage.qst.action;

import java.io.File;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cloudstorage.qst.domain.UploadFileInfo;
import cn.cloudstorage.qst.domain.User;
import cn.cloudstorage.qst.service.UserService;
import cn.cloudstorage.qst.util.FileUtil;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Component("uploadFileAction")
@Scope("prototype")
public class UploadFileAction extends ActionSupport implements ModelDriven<UploadFileInfo>{
	private static final long serialVersionUID = 1L;
	@Resource(name="userService")
	private UserService UserService ;
	private UploadFileInfo fileInfo=new UploadFileInfo();
	public UploadFileInfo getModel() {
		return this.fileInfo;
	}
	//文件上传
	public String upload(){
		try {
			User user_online=(User) ServletActionContext.getRequest().getSession().getAttribute("user_online");
            File file = fileInfo.getFile();			//文件对象
            if(user_online.getRestspace()-file.length()/(1024*1024.0f)<0.0f){//如果用户空间不足
            	addActionMessage("您的存储空间不足，开通会员即享5G大容量存储空间");
                return SUCCESS;
            }
            /*String chunk=fileInfo.getChunk();
            String chunks=fileInfo.getChunks();*/   //分块，分块上传时用
			//取得上传文件真实路径及名称
			String uploadFilePath=ServletActionContext.getServletContext()
					.getRealPath("/upload")+File.separator
					+fileInfo.getCurrentPath().replace("/", File.separator)
					+File.separator+fileInfo.getFileFileName();
			//测试使用的路径
			/*String uploadFilePath=ServletActionContext.getServletContext()
					.getRealPath("/upload")+File.separator+fileInfo.getFileFileName();*/
            FileUtil.randomAccessFile(uploadFilePath, file);
            //调用用户管理方法，更新用户剩余空间
            user_online.setRestspace(user_online.getRestspace()-file.length()/(1024*1024f));
            UserService.updateUser(user_online, null);	//更新用户剩余空间
            addActionMessage("文件上传成功");
            return SUCCESS;
		} catch (Exception e) {
			addActionMessage("文件上传错误");
			return ERROR;
		}
	}
	
}
