package cn.cloudstorage.qst.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import cn.cloudstorage.qst.domain.Friend;
import cn.cloudstorage.qst.domain.User;


public interface UserDao {
	public Serializable saveUser(User user);
	public User findUserByID(Long id);
	public List<User> findUserByPhone(String phone);
	public List<User> findUserByPhoneAndPassword(String phone,String password);
	public boolean updateUser(User user);
	//public boolean updatePasswordByID(User user);
	public boolean deleteUser(User user);
	
	public Set<Friend> findFriendsByID(Long id);
	
}
