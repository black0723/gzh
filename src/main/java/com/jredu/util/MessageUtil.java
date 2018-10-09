package com.jredu.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jredu.entity.TextMessage;

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
	 * 消息模板
	 * 
	 * @param message
	 * @return
	 */
	public static String msgTemplate(Map<String, String> map, List<Map<String, String>> news) {
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
			buffer.append("<ArticleCount>" + news.size() + "</ArticleCount>");
			buffer.append("<Articles>");
			for (Map<String, String> item : news) {
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
