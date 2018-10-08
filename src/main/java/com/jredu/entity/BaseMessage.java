package com.jredu.entity;

/**
 * 消息
 * 
 * @author Administrator
 *
 */
public class BaseMessage {
	private String ToUserName;
	private String FromUserName;
	private Long CreateTime;
	private String MsgType;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public BaseMessage() {
		
	}
	
	public BaseMessage(String toUserName, String fromUserName, Long createTime, String msgType) {
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
	}
}