package cn.cloudstorage.qst.domain;

import java.io.Serializable;
import java.util.Set;

public class User implements Serializable{
	/**
	 * 用户类，此类包含用户的所有个人信息
	 */
	private static final long serialVersionUID = 1L;
	private Long uid;				//用户ID
	private String username;		//用户名称
	private String phone;			//用户手机号---用于登录账号---唯一
	private String password;		//密码
	private String password_repeat;	//重复密码
	private Integer vip=0;			//是否是VIP
	private String avater;			//头像
	private String avaterurl;		//头像URL----供前端使用---不映射到数据库
	private String homepath;		//用户家目录
	private String mailbox;			//用户邮箱
	private Float restspace=2048.0f;//用户剩余空间
	private Set<Friend> friends;	//用户好友
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getVip() {
		return vip;
	}
	public void setVip(Integer vip) {
		this.vip = vip;
	}
	public String getAvater() {
		return avater;
	}
	public void setAvater(String avater) {
		this.avater = avater;
	}
	public String getHomepath() {
		return homepath;
	}
	public void setHomepath(String homepath) {
		this.homepath = homepath;
	}
	public Set<Friend> getFriends() {
		return friends;
	}
	public void setFriends(Set<Friend> friends) {
		this.friends = friends;
	}
	public Float getRestspace() {
		return restspace;
	}
	public void setRestspace(Float restspace) {
		this.restspace = restspace;
	}
	public String getPassword_repeat() {
		return password_repeat;
	}
	public void setPassword_repeat(String password_repeat) {
		this.password_repeat = password_repeat;
	}
	public String getMailbox() {
		return mailbox;
	}
	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}
	public String getAvaterurl() {
		return avaterurl;
	}
	public void setAvaterurl(String avaterurl) {
		this.avaterurl = avaterurl;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", phone="
				+ phone + ", password=" + password + ", password_repeat="
				+ password_repeat + ", vip=" + vip + ", avater=" + avater
				+ ", avaterurl=" + avaterurl + ", homepath=" + homepath
				+ ", mailbox=" + mailbox + ", restspace=" + restspace
				+ ", friends=" + friends + "]";
	}
}
