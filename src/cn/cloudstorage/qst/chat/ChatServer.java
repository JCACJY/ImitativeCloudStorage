package cn.cloudstorage.qst.chat;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import net.sf.json.JSONObject;


@ServerEndpoint("/websocket")
public class ChatServer {

	private static Vector<Session> room = new Vector<Session>();
	private Map<String,Session> map = new HashMap<String, Session>();//static
	
	/**
	 * 用户接入
	 * @param session 可选
	 */
	@OnOpen
	public void onOpen(Session session){
		room.addElement(session);
		String  queryString = session.getQueryString();
		String name = queryString.split("=")[1];
		this.map.put(name, session);
	}
	
	/**
	 * 接收到来自用户的消息
	 * @param message
	 * @param session
	 */
	@OnMessage
	public void onMessage(String message,Session session){

		//把用户发来的消息解析为JSON对象
		JSONObject obj = JSONObject.fromObject(message);
		String to = (String) obj.get("to");
		//System.out.println(map.get(to));
		//向JSON对象中添加发送时间
//		obj.put("date", df.format(new Date()));
		Session ss = map.get(to);
		//遍历聊天室中的所有会话
		for(Session se : room){
			if(se.equals(ss)) {
				//设置消息是否为自己的
				obj.put("isSelf", false);
				//System.out.println(obj.toString());
				//发送消息给远程用户
				se.getAsyncRemote().sendText(obj.toString());
				
				obj.put("isSelf", true);
				//System.out.println(obj.toString());
				session.getAsyncRemote().sendText(obj.toString());
			}
			
		}
	}
	
	/**
	 * 用户断开
	 * @param session
	 */
	@OnClose
	public void onClose(Session session){
		room.remove(session);
	}
	
	/**
	 * 用户连接异常
	 * @param t
	 */
	@OnError
	public void onError(Throwable t){
	}
}
