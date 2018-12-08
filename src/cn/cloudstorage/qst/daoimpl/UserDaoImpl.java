package cn.cloudstorage.qst.daoimpl;

import java.io.Serializable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import cn.cloudstorage.qst.dao.UserDao;
import cn.cloudstorage.qst.domain.Friend;
import cn.cloudstorage.qst.domain.User;



@SuppressWarnings("unchecked")
@Component("userDao")
public class UserDaoImpl implements UserDao {
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	//DAO业务方法
	//保存成功后会返回主键值
	public Serializable saveUser(User user) {
		Serializable result=this.hibernateTemplate.save(user); 
		return result;
	}

	public User findUserByID(Long id) {
		User user=(User) this.hibernateTemplate.load(User.class, id);
		return user;
	}

	public List<User> findUserByPhone(String phone) {
		List<User> user=this.hibernateTemplate.find("from User where phone=?", phone);
		return user;
	}

	public List<User> findUserByPhoneAndPassword(String phone, String password) {
		List<User> user=this.hibernateTemplate.find(
				"from User where phone=? and password=?", new String[]{phone,password});
		return user;
	}

	public boolean updateUser(User user) {
		this.hibernateTemplate.saveOrUpdate(user);
		return true;
	}
	/*public boolean updatePasswordByID(User user) {
		this.hibernateTemplate.saveOrUpdate(user);
		return true;
	}*/
	public boolean deleteUser(User user) {
		this.hibernateTemplate.delete(user);
		return true;
	}
	public Set<Friend> findFriendsByID(Long id) {
		List<Friend> friends=this.hibernateTemplate.find("from Friend where uid=?", id);
		return new HashSet<Friend>(friends);
	}

}
