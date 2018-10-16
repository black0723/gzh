package com.jredu.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jredu.entity.TextMessage;

/**
 * 发送给微信服务器的消息处理
 * 
 * @author Administrator
 *
 */
public class MessageUtil {

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
	 * 发送给微信服务器的消息模板
	 * 
	 * @param wxMap 微信返给我们的map
	 * @param news  图文消息的map
	 * @return
	 */
	public static String getMsgTemplate(Map<String, String> wxMap, List<Map<String, String>> wxMapNews) {
		// 从集合中，获取XML各个节点的内容
		String toUserName = wxMap.get("ToUserName");
		String fromUserName = wxMap.get("FromUserName");
		String createTime = wxMap.get("CreateTime");
		String msgType = wxMap.get("MsgType");
		String content = wxMap.get("Content");
		String msgId = wxMap.get("MsgId ");
		String mediaId = wxMap.get("MediaId ");
		String title = wxMap.get("Title ");
		String description = wxMap.get("Description ");
		String musicUrl = wxMap.get("MusicUrl ");
		String hQMusicUrl = wxMap.get("HQMusicUrl ");
		String thumbMediaId = wxMap.get("ThumbMediaId ");

		StringBuffer buffer = new StringBuffer();
		buffer.append("<xml>");
		buffer.append("<ToUserName>");
		buffer.append("<![CDATA[" + toUserName + "]]>");
		buffer.append("</ToUserName>");

		buffer.append("<FromUserName>");
		buffer.append("<![CDATA[" + fromUserName + "]]>");
		buffer.append("</FromUserName>");

		buffer.append("<CreateTime>");
		buffer.append("<![CDATA[" + new Date().getTime() + "]]>");
		buffer.append("</CreateTime>");

		buffer.append("<MsgType>");
		buffer.append("<![CDATA[" + msgType + "]]>");
		buffer.append("</MsgType>");

		if (MESSAGE_TEXT.equals(msgType)) {
			TextMessage message = new TextMessage(toUserName, fromUserName, new Date().getTime(), msgType);
			message.setContent(content);
			return XmlUtil.textMessageToXml(message);
		} else if (MESSAGE_IMAGE.equals(msgType)) {
			buffer.append("<Image><MediaId>");
			buffer.append("<![CDATA[" + mediaId + "]]>");
			buffer.append("</MediaId></Image>");
		} else if (MESSAGE_VOICE.equals(msgType)) {
			buffer.append("<Voice><MediaId>");
			buffer.append("<![CDATA[" + mediaId + "]]>");
			buffer.append("</MediaId></Voice>");
		} else if (MESSAGE_VIDEO.equals(msgType)) {
			buffer.append("<Video>");
			buffer.append("<MediaId><![CDATA[" + mediaId + "]]></MediaId>");
			buffer.append("<Title><![CDATA[" + title + "]]></Title>");
			buffer.append("<Description><![CDATA[" + description + "]]></Description>");
			buffer.append("</Video>");
		} else if (MESSAGE_MUSIC.equals(msgType)) {
			buffer.append("<Music>");
			buffer.append("<MediaId><![CDATA[" + mediaId + "]]></MediaId>");
			buffer.append("<Title><![CDATA[" + title + "]]></Title>");
			buffer.append("<Description><![CDATA[" + description + "]]></Description>");
			buffer.append("<MusicUrl><![CDATA[" + musicUrl + "]]></MusicUrl>");
			buffer.append("<HQMusicUrl><![CDATA[" + hQMusicUrl + "]]></HQMusicUrl>");
			buffer.append("<ThumbMediaId><![CDATA[" + thumbMediaId + "]]></ThumbMediaId>");
			buffer.append("</Music>");
		} else if (MESSAGE_NEWS.equals(msgType)) {
			buffer.append("<ArticleCount>" + wxMapNews.size() + "</ArticleCount>");
			buffer.append("<Articles>");
			for (Map<String, String> item : wxMapNews) {
				buffer.append("<item>");
				buffer.append("<Title><![CDATA[" + item.get("Title") + "]]></Title>");
				buffer.append("<Title><![CDATA[" + item.get("Description") + "]]></Title>");
				buffer.append("<Title><![CDATA[" + item.get("PicUrl") + "]]></Title>");
				buffer.append("<Title><![CDATA[" + item.get("Url") + "]]></Title>");
				buffer.append("</item>");
			}
			buffer.append("</Articles>");
		}
		buffer.append("</xml>");
		return buffer.toString();
	}

	/**
	 * 根据用户的回复 设置发送给微信服务器的XML内容
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
		String PicUrl = map.get("PicUrl ");
		String Recognition = map.get("Recognition ");

		String Location_X = map.get("Location_X ");
		String Location_Y = map.get("Location_Y ");
		String Scale = map.get("Scale ");
		String Label = map.get("Label ");

		Map<String, String> options = new HashMap<>();
		options.put("ToUserName", fromUserName);
		options.put("FromUserName", toUserName);
		options.put("CreateTime", System.currentTimeMillis() + "");
		options.put("MsgType", "text");

		String message = "您在说什么，我听不懂？";
		// 判断用户发送的消息是否是文本消息
		if (MESSAGE_TEXT.equals(msgType)) {
			// 判断用户发送的消息内容具体是什么
			if (content.equals("1")) {
				message = "大吉大利，今晚吃鸡";
			} else if (content.equals("2")) {
				message = "落地成盒";
			} else if (content.contains("爱")) {
				message = "我爱你~";
			}
		} else if (MESSAGE_IMAGE.equals(msgType)) {
			options.put("MsgType", "image");
			options.put("MediaId", mediaId);
			System.out.println(PicUrl);
		} else if (MESSAGE_VOICE.equals(msgType)) {
			options.put("MsgType", "voice");
			options.put("MediaId", mediaId);
			System.out.println(Recognition);
		} else if (MESSAGE_VIDEO.equals(msgType)) {

		} else if (MESSAGE_MUSIC.equals(msgType)) {

		} else if (MESSAGE_NEWS.equals(msgType)) {

		} else if (MESSAGE_LOCATION.equals(msgType)) {
			content = "纬度：" + Location_X + "经度：" + Location_Y + "缩放大小：" + Scale + "位置信息：" + Label;
		} else if (msgType.equals(MessageUtil.MESSAGE_EVENT)) {// 判断是否为事件类型
			// 从集合中，或许是哪一种事件传入
			String eventType = map.get("Event");
			// 关注事件 //用户订阅事件
			if (eventType.equals(MessageUtil.MESSAGE_SUBSCRIBE)) {
				content = "欢迎您的关注~";
				// EventKey 事件KEY值，qrscene_为前缀，后面为二维码的参数值
				if (map.get("EventKey") != null) {
					content = "用户扫描带参数的二维码关注事件";
				}
			} else if (eventType.equals(MessageUtil.MESSAGE_UNSUBSCRIBE)) {
				// 用户取消订阅事件
				content = "无情取关~";
			} else if (eventType.equals("SCAN")) {
				content = "用户已经关注过，再扫描带参数的二维码关注事件";
			} else if (eventType.equals("LOCATION")) {
				content = "纬度：" + map.get("Latitude") + "经度：" + map.get("Longitude") + "精度：" + map.get("Precision");
			} else if (eventType.equals("CLICK")) {
				content = "您点击了按钮:${message.EventKey}";
			}
		}

		options.put("Content", message);
		return MessageUtil.getMsgTemplate(options, null);
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
