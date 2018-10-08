package com.jredu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.jredu.entity.BaseMessage;
import com.jredu.entity.TextMessage;
import com.thoughtworks.xstream.XStream;

public class MessageUtil {

	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
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
	public static String msgTemplate(Map<String, String> map) {
		// 从集合中，获取XML各个节点的内容
		String toUserName = map.get("ToUserName");
		String fromUserName = map.get("FromUserName");
		String createTime = map.get("CreateTime");
		String msgType = map.get("MsgType");
		String content = map.get("Content");
		String msgId = map.get("MsgId ");

		if (MESSAGE_TEXT.equals(msgType)) {
			TextMessage message = new TextMessage(toUserName, fromUserName, new Date().getTime(), msgType);
			message.setContent(content);
			return XmlUtil.textMessageToXml(message);
		} else if (MESSAGE_IMAGE.equals(msgType)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("<xml>");
			buffer.append("<ToUserName>");
			buffer.append("<![CDATA["+toUserName+"]]>");
			buffer.append("</ToUserName>");
			
			buffer.append("<FromUserName>");
			buffer.append("<![CDATA["+fromUserName+"]]>");
			buffer.append("</FromUserName>");
			
			buffer.append("<CreateTime>");
			buffer.append("<![CDATA["+new Date().getTime()+"]]>");
			buffer.append("</CreateTime>");
			
			buffer.append("<MsgType>");
			buffer.append("<![CDATA[image]]>");
			buffer.append("</MsgType>");

			buffer.append("<Image>");
			buffer.append("<MediaId>");
			buffer.append("<![CDATA["+fromUserName+"]]>");
			buffer.append("</MediaId>");
			buffer.append("</Image>");
			buffer.append("</xml>");
			return buffer.toString();
		} else if (MESSAGE_TEXT.equals(msgType)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("<xml>");
			buffer.append("<ToUserName>");
			buffer.append("<![CDATA["+toUserName+"]]>");
			buffer.append("</ToUserName>");
			
			buffer.append("<FromUserName>");
			buffer.append("<![CDATA["+fromUserName+"]]>");
			buffer.append("</FromUserName>");
			
			buffer.append("<CreateTime>");
			buffer.append("<![CDATA["+new Date().getTime()+"]]>");
			buffer.append("</CreateTime>");
			
			buffer.append("<MsgType>");
			buffer.append("<![CDATA[image]]>");
			buffer.append("</MsgType>");

			buffer.append("<Image>");
			buffer.append("<MediaId>");
			buffer.append("<![CDATA["+fromUserName+"]]>");
			buffer.append("</MediaId>");
			buffer.append("</Image>");
			buffer.append("</xml>");
			return buffer.toString();
		} else if (MESSAGE_TEXT.equals(msgType)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("<xml>");
			buffer.append("<ToUserName>");
			buffer.append("<![CDATA["+toUserName+"]]>");
			buffer.append("</ToUserName>");
			
			buffer.append("<FromUserName>");
			buffer.append("<![CDATA["+fromUserName+"]]>");
			buffer.append("</FromUserName>");
			
			buffer.append("<CreateTime>");
			buffer.append("<![CDATA["+new Date().getTime()+"]]>");
			buffer.append("</CreateTime>");
			
			buffer.append("<MsgType>");
			buffer.append("<![CDATA[image]]>");
			buffer.append("</MsgType>");

			buffer.append("<Image>");
			buffer.append("<MediaId>");
			buffer.append("<![CDATA["+fromUserName+"]]>");
			buffer.append("</MediaId>");
			buffer.append("</Image>");
			buffer.append("</xml>");
			return buffer.toString();
		} else if (MESSAGE_TEXT.equals(msgType)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("<xml>");
			buffer.append("<ToUserName>");
			buffer.append("<![CDATA["+toUserName+"]]>");
			buffer.append("</ToUserName>");
			
			buffer.append("<FromUserName>");
			buffer.append("<![CDATA["+fromUserName+"]]>");
			buffer.append("</FromUserName>");
			
			buffer.append("<CreateTime>");
			buffer.append("<![CDATA["+new Date().getTime()+"]]>");
			buffer.append("</CreateTime>");
			
			buffer.append("<MsgType>");
			buffer.append("<![CDATA[image]]>");
			buffer.append("</MsgType>");

			buffer.append("<Image>");
			buffer.append("<MediaId>");
			buffer.append("<![CDATA["+fromUserName+"]]>");
			buffer.append("</MediaId>");
			buffer.append("</Image>");
			buffer.append("</xml>");
			return buffer.toString();
		} else if (MESSAGE_TEXT.equals(msgType)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("<xml>");
			buffer.append("<ToUserName>");
			buffer.append("<![CDATA["+toUserName+"]]>");
			buffer.append("</ToUserName>");
			
			buffer.append("<FromUserName>");
			buffer.append("<![CDATA["+fromUserName+"]]>");
			buffer.append("</FromUserName>");
			
			buffer.append("<CreateTime>");
			buffer.append("<![CDATA["+new Date().getTime()+"]]>");
			buffer.append("</CreateTime>");
			
			buffer.append("<MsgType>");
			buffer.append("<![CDATA[image]]>");
			buffer.append("</MsgType>");

			buffer.append("<Image>");
			buffer.append("<MediaId>");
			buffer.append("<![CDATA["+fromUserName+"]]>");
			buffer.append("</MediaId>");
			buffer.append("</Image>");
			buffer.append("</xml>");
			return buffer.toString();
		} else {
			return null;
		}
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
