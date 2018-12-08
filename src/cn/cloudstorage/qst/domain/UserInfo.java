package cn.cloudstorage.qst.domain;

import java.io.File;

public class UserInfo {
	private String username;			//用户名
	private String password;			//旧密码---明文
	private String newpassword;			//新密码
	private String repetitivepassword;	//重复密码
	private Integer vip;				//VIP
	private String avater;				//用户头像
	private Float restspace=2048.0f;	//剩余空间
	private String mailbox;				//邮箱
	private File image;					//头像
	private String imageFileName;		//头像图片名称
	private String imageContentType;	//头像类型
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public Float getRestspace() {
		return restspace;
	}
	public void setRestspace(Float restspace) {
		this.restspace = restspace;
	}
	public String getRepetitivepassword() {
		return repetitivepassword;
	}
	public void setRepetitivepassword(String repetitivepassword) {
		this.repetitivepassword = repetitivepassword;
	}
	public String getMailbox() {
		return mailbox;
	}
	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", password=" + password
				+ ", repetitivepassword=" + repetitivepassword + ", vip=" + vip
				+ ", avater=" + avater + ", restspace=" + restspace
				+ ", mailbox=" + mailbox + ", image=" + image
				+ ", imageFileName=" + imageFileName + ", imageContentType="
				+ imageContentType + "]";
	}
}
