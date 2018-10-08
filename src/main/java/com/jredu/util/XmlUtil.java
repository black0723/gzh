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

public class XmlUtil {

	/**
	 * 
	 * 将XML转为MAP集合
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 * 
	 */

	public static Map<String, String> xmlToMap(HttpServletRequest request) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();

		// 从request对象中获取输入流
		InputStream ins = request.getInputStream();

		// 使用reader对象读取输入流,解析为XML文档
		Document doc = reader.read(ins);

		// 获取XML根元素
		Element root = doc.getRootElement();

		// 将根元素的所有节点，放入列表中
		List<Element> list = root.elements();

		// 遍历list对象，并保存到集合中
		for (Element element : list) {
			map.put(element.getName(), element.getText());
		}

		ins.close();
		return map;
	}

	/**
	 * 
	 * 将文本消息对象转成XML
	 * @param text
	 * @return
	 * 
	 */
	public static String textMessageToXml(BaseMessage textMessage) {
		XStream xstream = new XStream();
		// 将xml的根节点替换成<xml> 默认为TextMessage的包名
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
}
