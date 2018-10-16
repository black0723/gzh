package com.jredu.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpUtil {

	public static String doGet(String url, String charset) {

		/* 1 生成 HttpClinet 对象并设置参数 */
		HttpClient httpClient = new HttpClient();
		// 设置 Http 连接超时为5秒
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

		/* 2 生成 GetMethod 对象并设置参数 */
		GetMethod getMethod = new GetMethod(url);
		// 设置 get 请求超时为 5 秒
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		// 设置请求重试处理，用的是默认的重试处理：请求三次
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

		getMethod.setRequestHeader("Accept", "*/*");
		getMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
		getMethod.setRequestHeader("Cache-Control", "no-cache");
		getMethod.setRequestHeader("Connection", "keep-alive");
		getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

		String response = "";
		/* 3 执行 HTTP GET 请求 */
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			/* 4 判断访问的状态码 */
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}
			/* 5 处理 HTTP 响应内容 */
			// HTTP响应头部信息，这里简单打印
			Header[] headers = getMethod.getResponseHeaders();
			for (Header h : headers) {
				// System.out.println(h.getName() + "------------ " + h.getValue());
			}
			// 读取 HTTP 响应内容，这里简单打印网页内容
			byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
			response = new String(responseBody, charset);
			// System.out.println(response);
			// 读取为 InputStream，在网页内容数据量大时候推荐使用
			// InputStream response = getMethod.getResponseBodyAsStream();

		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			/* 6 .释放连接 */
			getMethod.releaseConnection();
		}
		return response;
	}

	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 * 
	 * @param url     请求的URL地址
	 * @param params  请求的查询参数,可以为null
	 * @param charset 字符集
	 * @param pretty  是否美化
	 * @return 返回请求响应的HTML
	 */
	public static String doPost(String url, Map<String, Object> _params, String charset, boolean pretty) {

		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);

		// 设置Http Post数据
		if (_params != null) {
			for (Map.Entry<String, Object> entry : _params.entrySet()) {
				method.setParameter(entry.getKey(), String.valueOf(entry.getValue()));
			}
		}

		method.setRequestHeader("Accept", "*/*");
		// method.setRequestHeader("Accept-Encoding", "gzip, deflate");
		// method.setRequestHeader("Accept-Language",
		// "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
		// method.setRequestHeader("Cache-Control", "no-cache");
		// method.setRequestHeader("Connection", "keep-alive");
		method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		// method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		// // 设置发送数据的
		try {
			client.executeMethod(method);
			// System.out.println("method.getStatusCode()-->" + method.getStatusCode());
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				// 读取为 InputStream，在网页内容数据量大时候推荐使用
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(method.getResponseBodyAsStream(), charset));

				String line;
				while ((line = reader.readLine()) != null) {
					// System.out.println(line);
					if (pretty) {
						response.append(line).append(System.getProperty("line.separator"));
					} else {
						response.append(line).append("\r\n");
					}
				}
				reader.close();
			}
		} catch (IOException e) {
			System.out.println("执行HTTP Post请求" + url + "时，发生异常！");
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		// System.out.println("--------------------" + response.toString());
		return response.toString();
	}

	/**
	 * 发送POST请求
	 * @param action
	 * @param params
	 * @return
	 */
	public static String doPost2(String action,String params) {
		//String menu = "{\"button\":[{\"type\":\"click\",\"name\":\"项目管理\",\"key\":\"20_PROMANAGE\"},{\"type\":\"click\",\"name\":\"机构运作\",\"key\":\"30_ORGANIZATION\"},{\"name\":\"日常工作\",\"sub_button\":[{\"type\":\"click\",\"name\":\"待办工单\",\"key\":\"01_WAITING\"},{\"type\":\"click\",\"name\":\"已办工单\",\"key\":\"02_FINISH\"},{\"type\":\"click\",\"name\":\"我的工单\",\"key\":\"03_MYJOB\"},{\"type\":\"click\",\"name\":\"公告消息箱\",\"key\":\"04_MESSAGEBOX\"},{\"type\":\"click\",\"name\":\"签到\",\"key\":\"05_SIGN\"}]}]}";
		// 此处改为自己想要的结构体，替换即可
		try {
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			//System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			//System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(params.getBytes("UTF-8"));// 传入参数
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			return message;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

}
