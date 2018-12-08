package cn.cloudstorage.qst.domain;

import java.io.Serializable;

public class Friend implements Serializable{
	/**
	 * 描述了用户好友
	 * 通过User正向关联friend
	 */
	private static final long serialVersionUID = 1L;
	private Long fid;			//好友ID
	private String friendname;	//好友名称
	private Integer fvip;		//好友VIP
	private String friendavater;//好友头像
	private String friendavaterurl;//好友头像url
	private String friendgroup;	//好友分组
	//private User user;		//反向级联用户
	public Long getFid() {
		return fid;
	}
	public void setFid(Long fid) {
		this.fid = fid;
	}
	public String getFriendname() {
		return friendname;
	}
	public void setFriendname(String friendname) {
		this.friendname = friendname;
	}
	public Integer getFvip() {
		return fvip;
	}
	public void setFvip(Integer fvip) {
		this.fvip = fvip;
	}
	public String getFriendavater() {
		return friendavater;
	}
	public void setFriendavater(String friendavater) {
		this.friendavater = friendavater;
	}
	/*public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}*/
	public String getFriendgroup() {
		return friendgroup;
	}
	public void setFriendgroup(String friendgroup) {
		this.friendgroup = friendgroup;
	}
	public String getFriendavaterurl() {
		return friendavaterurl;
	}
	public void setFriendavaterurl(String friendavaterurl) {
		this.friendavaterurl = friendavaterurl;
	}
	@Override
	public String toString() {
		return "Friend [fid=" + fid + ", friendname=" + friendname + ", fvip="
				+ fvip + ", friendavater=" + friendavater
				+ ", friendavaterurl=" + friendavaterurl + ", friendgroup="
				+ friendgroup + "]";
	}
}
