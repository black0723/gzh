package com.jredu.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jredu.entity.TextMessage;

/**
 * 根据用户输入的信息，回复给用户不同的内容
 * @author Administrator
 *
 */
public class ReplyUtil {

	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_MUSIC = "music";
	public static final String MESSAGE_NEWS = "news";

	public static final String MESSAGE_SHORTVIDEO = "shortvideo";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	public static final String MESSAGE_SCAN = "SCAN";

	/**
	 * 消息模板
	 * 
	 * @param message
	 * @return
	 */
	public static String setReplyMsg(Map<String, String> map) {
		
		// 从集合中，获取XML各个节点的内容
		String toUserName = map.get("ToUserName");
		String fromUserName = map.get("FromUserName");
		String createTime = map.get("CreateTime");
		String msgType = map.get("MsgType");
		String content = map.get("Content");
		String msgId = map.get("MsgId ");
		String mediaId = map.get("MediaId ");
		String title = map.get("Title ");
		String description = map.get("Description ");
		String musicUrl = map.get("MusicUrl ");
		String hQMusicUrl = map.get("HQMusicUrl ");
		String thumbMediaId = map.get("ThumbMediaId ");

		Map<String, String> options = new HashMap<>();
		options.put("toUserName", fromUserName);
		options.put("fromUserName", toUserName);
		options.put("createTime", System.currentTimeMillis()+"");
		options.put("msgType", "text");
		
		String message = "您在说什么，我听不懂？";
		//判断用户发送的消息是否是文本消息
		if (MESSAGE_TEXT.equals(msgType)) {
			//判断用户发送的消息内容具体是什么
			if (content.equals("1")) {
				message = "大吉大利，今晚吃鸡";
			} else if (content.equals("2")) {
				message = "落地成盒";
			} else if (content.contains("爱")) {
				message = "我爱你~";
			} 
		} else if (MESSAGE_IMAGE.equals(msgType)) {
			
		} else if (MESSAGE_VOICE.equals(msgType)) {

		} else if (MESSAGE_VIDEO.equals(msgType)) {

		} else if (MESSAGE_MUSIC.equals(msgType)) {
			
		} else if (MESSAGE_NEWS.equals(msgType)) {
			
		}else if (msgType.equals(MessageUtil.MESSAGE_EVENT)) {// 判断是否为事件类型
			// 从集合中，或许是哪一种事件传入
			String eventType = map.get("Event");
			// 关注事件
			if (eventType.equals(MessageUtil.MESSAGE_SUBSCRIBE)) {
				//message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
			}
		}
		
		return content;
	}

	/**
	 * 
	 * 拼接关注主菜单
	 * 
	 */

	public static String menuText() {

		StringBuffer sb = new StringBuffer();

		sb.append("欢迎关注史上最帅公众号，请选择:\n\n");

		sb.append("1、姜浩真帅。\n");

		sb.append("2、姜浩并不帅。\n\n");

		sb.append("回复？调出主菜单。\n\n");

		return sb.toString();

	}
}
