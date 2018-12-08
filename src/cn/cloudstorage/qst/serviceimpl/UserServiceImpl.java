package cn.cloudstorage.qst.serviceimpl;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cloudstorage.qst.dao.UserDao;
import cn.cloudstorage.qst.domain.Friend;
import cn.cloudstorage.qst.domain.User;
import cn.cloudstorage.qst.service.UserService;
import cn.cloudstorage.qst.util.MD5Util;

@Component("userService")
public class UserServiceImpl implements UserService {
	@Resource(name="userDao")
	private UserDao userDao;
	//业务方法
	public boolean saveUser(User user) {
		if(user!=null){
			String uuidcode=UUID.randomUUID().toString();
			user.setPassword(MD5Util.encoding(user.getPassword()));	//密码加密
			user.setHomepath(uuidcode+"_"+user.getPhone());			//组合用户home目录ID
			user.setUsername(user.getPhone());			  			//初始化用户名和账号一致
			user.setAvater(user.getPhone()+".jpg");					//组合用户头像ID----生成默认图片
			Long result=(Long) userDao.saveUser(user);
			if(result==user.getUid()){
				return true;
			}
		}
		return false;
	}

	public User findUserByID(Long id) {
		if(id!=null||"".equals(id)){
			User user=userDao.findUserByID(id);
			return user;
		}
		return null;
	}
	
	public User findUserByphone(String phone) {
		if(phone!=null||"".equals(phone)){
			List<User> user=userDao.findUserByPhone(phone);
			if(user.size()>0){
				return user.get(0);
			}
		}
		return null;
	}
	
	public boolean isExistUser(String phone) {
		if(phone!=null){
			List<User> user=userDao.findUserByPhone(phone);
			if(user.size()>0){
				return true;
			}
		}
		return false;
	}

	public User findUserByPhoneAndPassword(String phone, String password) {
		if((phone!=null||"".equals(phone))&&(password!=null||"".equals(password))){
			List<User> user=userDao.findUserByPhoneAndPassword(phone, MD5Util.encoding(password.trim()));
			if(user.size()>0){
				return user.get(0);
			}
		}
		return null;
	}

	public String updateUser(User user,String newpassword) {
		if(user!=null){
			if(newpassword!=null||"".equals(newpassword)){//客户端执行的是修改密码操作，执行后客户端退出
				user.setPassword(MD5Util.encoding(newpassword));
				userDao.updateUser(user);
				return "changepasswd";
			}else{//客户端执行的是普通操作
				userDao.updateUser(user);
				return "changeinfo";
			}
		}
		return null;
	}
	
	public boolean deleteUser(User user) {//执行后客户端退出
		if(user!=null){
			userDao.deleteUser(user);
			return true;
		}
		return false;
	}
	//添加好友，按理说应该从friend端简历关联效率才会高，但这样会更易理解
	public boolean saveFriend(Friend friend,Long uid) {
		if(friend!=null&&uid!=0){
			User user=userDao.findUserByID(uid);
			user.getFriends().add(friend);
			Long result=(Long) userDao.saveUser(user);
			if(result==user.getUid()){
				return true;
			}
		}
		return false;
	}
	//同上
	public boolean deleteFriend(Friend friend,Long uid) {
		if(friend!=null&&uid!=0){
			User user=userDao.findUserByID(uid);
			Set<Friend> friends=user.getFriends();
			friends.remove(friend);
			Long result=(Long) userDao.saveUser(user);
			if(result==user.getUid()){
				return true;
			}
		}
		return false;
	}
	
	public Set<Friend> findFriends(Long uid) {
		if(uid!=0){
			Set<Friend> friends=userDao.findFriendsByID(uid);
			return friends;
		}
		return null;
	}
}
