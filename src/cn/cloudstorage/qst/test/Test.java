package cn.cloudstorage.qst.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.cloudstorage.qst.domain.User;
import cn.cloudstorage.qst.service.UserService;

public class Test {
	
	@org.junit.Test
	public void test1(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService us=(UserService) ac.getBean("userService");
		User user=new User();
		user.setPhone("1234");
		user.setPassword("1234");
		us.saveUser(user);
	}

}
