package cn.cloudstorage.qst.action;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cloudstorage.qst.domain.User;
import cn.cloudstorage.qst.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户管理功能
 */
@Component("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User> {
	private static final long serialVersionUID = 1L;
	private User user=new User();
	@Resource(name="userService")
	private UserService userService;
	public User getModel() {
		return this.user;
	}
	//用户注册
	public String register(){
		try {
			boolean isExist=userService.isExistUser(user.getPhone());
			if(isExist){//已经被注册过
				addActionMessage("该用户已被注册");
				return INPUT;
			}
			boolean result=userService.saveUser(user);
			if(result){
				String avaterpath=ServletActionContext.getServletContext().getRealPath("/avater");
				FileUtils.copyFile(new File(avaterpath+File.separator+"default.jpg"),		//生成默认用户头像
								   new File(avaterpath+File.separator,user.getPhone()+".jpg"), false);
				return SUCCESS;
			}else{
				return INPUT;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	//用户登录
	public String login(){
		try {
			User user_online=userService.findUserByPhoneAndPassword(user.getPhone(), user.getPassword());
			if(user_online==null){//登录失败
				addActionMessage("账号或密码不正确");
				return INPUT;
			}
			HttpServletRequest request=ServletActionContext.getRequest();
			String applicationURL = request.getScheme()+"://"+request.getServerName()	//应用的URL
					   +":"+request.getServerPort()+request.getContextPath();
			user_online.setAvaterurl(applicationURL+"/avater/"+user_online.getAvater());//用户头像转成url
			HttpSession session=ServletActionContext.getRequest().getSession();
			session.setAttribute("user_online", user_online);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
	}
	
	//用户注销
	public String logout(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.removeAttribute("user_online");
		return SUCCESS;
	}
	
	//注册验证
	public void validateRegister() {
		if(user.getPhone()==null||"".equals(user.getPhone())){
			addFieldError("phone", "账号不能为空");
			return;
		}
			
		if(user.getPassword()==null||"".equals(user.getPassword()))
		{
			addFieldError("password", "密码不能为空");
			return;
		}
		if(!user.getPassword().equals(user.getPassword_repeat()))
			addFieldError("password", "两次密码不一致");
	}
	//登录验证
	public void validateLogin() {
		if(user.getPhone()==null||"".equals(user.getPhone())){
			addFieldError("phone", "账号不能为空");
			return;
		}
		if(user.getPassword()==null||"".equals(user.getPassword()))
			addFieldError("password", "密码不能为空");
	}
	
}
