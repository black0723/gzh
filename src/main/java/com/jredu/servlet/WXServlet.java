package com.jredu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jredu.util.CheckUtil;
import com.jredu.util.MessageUtil;

@WebServlet("/WXServlet")
public class WXServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println(request.getMethod());
		// 微信的加密签名
		String signature = request.getParameter("signature");
		// 微信的发送请求时间戳
		String timestamp = request.getParameter("timestamp");
		// 微信的随机数字
		String nonce = request.getParameter("nonce");
		// 微信的随机字符串
		String echostr = request.getParameter("echostr");

		System.out.println("signature->" + signature);
		System.out.println("timestamp->" + timestamp);
		System.out.println("nonce->" + nonce);
		System.out.println("echostr->" + echostr);

		PrintWriter out = response.getWriter();
		if (request.getMethod().equals("GET")) {
			if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
				// 如果校验成功，说明消息来自于微信服务器，返回echostr给微信服务器
				out.print(echostr);
			} else {
				// 如果不一样，说明不是微信服务器发送的消息，返回error
				out.print("error");
				System.out.println("checkSignature GET失败");
			}
		} else if (request.getMethod().equals("POST")) {
			if (!CheckUtil.checkSignature(signature, timestamp, nonce)) {
				// 说明消息不是微信服务器
				out.print("error");
				System.out.println("checkSignature POST失败");
			} else {
				// 将request请求，传到Message工具类的转换方法中，返回接收到的Map对象
				try {
					Map<String, String> map = MessageUtil.xmlToMap(request);
					// 从集合中，获取XML各个节点的内容
					String ToUserName = map.get("ToUserName");
					String FromUserName = map.get("FromUserName");
					String CreateTime = map.get("CreateTime");
					String MsgType = map.get("MsgType");
					String Content = map.get("Content");
					String MsgId = map.get("MsgId ");
					System.out.println("Content-->" + Content);

					String message = null;
					// 判断是否为文本消息类型
					if (MsgType.equals(MessageUtil.MESSAGE_TEXT)) {
						if (Content.equals("1")) {
							message = MessageUtil.initText(ToUserName, FromUserName, "对啊！我也是这么觉得！帅哭了！");
						} else if (Content.equals("2")) {
							message = MessageUtil.initText(ToUserName, FromUserName, "好可怜啊！你年级轻轻地就瞎了！");
						} else if (Content.equals("?") || Content.equals("？")) {
							message = MessageUtil.initText(ToUserName, FromUserName, MessageUtil.menuText());
						} else {
							message = MessageUtil.initText(ToUserName, FromUserName, "没让你选的就别瞎嘚瑟！！！");
						}
					} else if (MsgType.equals(MessageUtil.MESSAGE_EVENT)) {// 判断是否为事件类型
						// 从集合中，或许是哪一种事件传入
						String eventType = map.get("Event");

						// 关注事件
						if (eventType.equals(MessageUtil.MESSAGE_SUBSCRIBE)) {
							message = MessageUtil.initText(ToUserName, FromUserName, MessageUtil.menuText());
						}
					}
					System.out.println("message-->" + message);
					out.println(message);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					out.close();
				}
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
