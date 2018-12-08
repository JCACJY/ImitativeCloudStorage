package cn.cloudstorage.qst.action;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cloudstorage.qst.domain.User;
import cn.cloudstorage.qst.domain.UserInfo;
import cn.cloudstorage.qst.service.UserService;
import cn.cloudstorage.qst.util.MD5Util;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 用于修改信息
 */
@Component("changeInfoAction")
@Scope("prototype")
public class ChangeInfoAction extends ActionSupport implements ModelDriven<UserInfo>{
	private static final long serialVersionUID = 1L;
	@Resource(name="userService")
	private UserService userService;
	private UserInfo userInfo=new UserInfo();
	public UserInfo getModel() {
		return this.userInfo;
	}
	
	//用户修改信息
	public String modifyBaseInfo(){
		try {
			HttpSession session=ServletActionContext.getRequest().getSession();
			User user_online=(User) session.getAttribute("user_online");
			if("image/jpeg".equals(userInfo.getImageContentType())){//判断上传文件类型----只限jpg格式图片
				String avaterPath=ServletActionContext.getServletContext().getRealPath("/avater");
				FileUtils.copyFile(userInfo.getImage(),				//更换用户头像
						   new File(avaterPath+File.separator,user_online.getAvater()), false);
			}
			user_online.setUsername(userInfo.getUsername());		//用户名
			user_online.setMailbox(userInfo.getMailbox());
			/*user_online.setVip(userInfo.getVip());				//VIP
			user_online.setRestspace(userInfo.getRestspace());*/	//剩余空间
			String result=userService.updateUser(user_online, null);//修改普通信息
			System.out.println(result);
			if("changeinfo".equals(result)){
				addActionMessage("修改成功");
				return SUCCESS;
			}
			addActionMessage("修改失败");
			return INPUT;
		} catch (Exception e) {
			addActionMessage("修改失败");
			e.printStackTrace();
			return ERROR;
		}
	}
	//修改密码
	public String modifyPassword(){
		try {
			HttpSession session=ServletActionContext.getRequest().getSession();
			User user_online=(User) session.getAttribute("user_online");
			if(!MD5Util.encoding(userInfo.getPassword()).equals(user_online.getPassword())){//判断旧密码是否与框中一致
				addActionMessage("旧密码不正确");
				return INPUT;
			}
			String result=userService.updateUser(user_online, userInfo.getNewpassword());
			if("changepasswd".equals(result)){//修改密码成功，需要重新登录
				session.removeAttribute("user_online");
				return SUCCESS;
			}
			addActionMessage("修改失败");
			return INPUT;
		} catch (Exception e) {
			addActionMessage("修改失败");
			return ERROR;
		}
	}
	//基本信息验证
	public void validateModifyBaseInfo() {
		if(userInfo.getUsername()==null||"".equals(userInfo.getUsername())){
			addFieldError("username", "用户名不能为空！");
		}
		if(userInfo.getImage()!=null&&(!"image/jpeg".equals(userInfo.getImageContentType()))){//如果有文件，但文件不是jpg格式
			addFieldError("iamge", "头像仅支持jpg格式的图片");
		}
	}
	//验证密码
	public void validateModifyPassword() {
		if(userInfo.getPassword()==null||"".equals(userInfo.getPassword())){
			addFieldError("password", "原密码不能为空");
			return;
		}
		if(userInfo.getNewpassword()==null||"".equals(userInfo.getNewpassword())){
			addFieldError("password", "新密码不能为空");
			return;
		}
		if(!userInfo.getNewpassword().equals(userInfo.getRepetitivepassword())){
			addFieldError("password", "两次新密码不一致");
			return;
		}
		if(userInfo.getPassword().equals(userInfo.getNewpassword())){
			addFieldError("password", "新密码和旧密码一致，无需修改");
		}
	}
}
