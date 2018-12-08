package cn.cloudstorage.qst.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class PermissionInterceptor implements Interceptor {
	/**
	 * 用户登录权限拦截
	 */
	private static final long serialVersionUID = 1L;
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpSession session=ServletActionContext.getRequest().getSession();
		Object user_online = session.getAttribute("user_online");
		if(user_online==null){
			System.out.println("用户未登录，跳到登录页");
			return "login";				//配置拦截器,定义全局的login视图
		}else{
			System.out.println("action:"+invocation.getAction()+"被拦截,马上放行");
			return invocation.invoke();	//执行动作方法
		}
	}
	public void init() {
		// 初始化，注册、登录动作不要拦截
	}
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
