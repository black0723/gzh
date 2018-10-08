package com.jredu.entity;

public class TextMessage extends BaseMessage {
	private String Content;
	private String MsgId;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	public TextMessage(String toUserName, String fromUserName, Long createTime, String msgType, String content,
			String msgId) {
		super(toUserName, fromUserName, createTime, msgType);
		Content = content;
		MsgId = msgId;
	}
	
	public TextMessage(String toUserName, String fromUserName, Long createTime, String msgType) {
		super(toUserName, fromUserName, createTime, msgType);
	}

	public TextMessage() {
		super();
	}
}
