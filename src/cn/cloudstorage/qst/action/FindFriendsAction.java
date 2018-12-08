package cn.cloudstorage.qst.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cloudstorage.qst.domain.Friend;
import cn.cloudstorage.qst.domain.User;

import com.opensymphony.xwork2.ActionSupport;

@Component("findFriendsAction")
@Scope("prototype")
public class FindFriendsAction extends ActionSupport{
	/**
	 * 查找用户的所有与好友，按照分组返回给客户端
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, List<Friend>> friends_group;
	//为用户好友分类
	public String findFriends(){
		try {
			HttpServletRequest request= ServletActionContext.getRequest();
			User user_online=(User) request.getSession().getAttribute("user_online");
			//拼接网络地址
			String baseUrl = request.getScheme()+"://"+request.getServerName()
					   				+":"+request.getServerPort()+request.getContextPath()
					   				+"/avater/";
			Set<Friend> friends=user_online.getFriends();			//取得用户所有好友
			List<Friend> friend_list;								//存储同一组的好友
			friends_group=new HashMap<String, List<Friend>>();		//按组存储用户好友
			Set<String> groupName_set=new HashSet<String>();		//存储所有组名--set
			for (Friend friend : friends) {							
				groupName_set.add(friend.getFriendgroup());
			}
			ArrayList<String> groupName_list=new ArrayList<String>(groupName_set);//存储所有组名--list
			
			for (int i = 0; i < groupName_list.size(); i++) {		//按组名进行分拣好友
				friend_list=new ArrayList<Friend>();
				for (Friend friend : friends) {
					if(groupName_list.get(i).equals(friend.getFriendgroup())){
						//friend.setFriendavater(baseUrl+friend.getFriendavater());
						friend.setFriendavaterurl(baseUrl+friend.getFriendavater());
						friend_list.add(friend);
					}
				}
				friends_group.put(groupName_list.get(i), friend_list);
			}
			//System.out.println(friends_group);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
	//setter、getter
	@JSON(serialize=true)
	public Map<String, List<Friend>> getFriends_group() {return friends_group;}
	public void setFriends_group(Map<String, List<Friend>> friends_group) {this.friends_group = friends_group;}
}
