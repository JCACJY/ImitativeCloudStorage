package cn.cloudstorage.qst.service;

import java.util.Set;

import cn.cloudstorage.qst.domain.Friend;
import cn.cloudstorage.qst.domain.User;



public interface UserService {
	public boolean saveUser(User user);
	public User findUserByID(Long id);
	public User findUserByphone(String phone);
	public boolean isExistUser(String phone);
	public User findUserByPhoneAndPassword(String phone,String password);
	public String updateUser(User user ,String newpassword);
	//public boolean updatePasswordByID(User user);
	public boolean deleteUser(User user);
	
	public boolean saveFriend(Friend friend,Long uid);
	public boolean deleteFriend(Friend friend,Long uid);
	public Set<Friend> findFriends(Long uid);
}
