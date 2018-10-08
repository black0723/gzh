package com.jredu.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	 * @param url  请求的URL地址 
	 * @param params  请求的查询参数,可以为null 
	 * @param charset 字符集 
	 * @param pretty 是否美化 
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

		// 设置Http Post数据 方法二
		// if(_params != null) {
		// NameValuePair[] pairs = new NameValuePair[_params.size()];//纯参数了，键值对
		// int i = 0;
		// for (Map.Entry<String, Object> entry : _params.entrySet()) {
		// pairs[i] = new NameValuePair(entry.getKey(),
		// String.valueOf(entry.getValue()));
		// i++;
		// }
		// method.addParameters(pairs);
		// }

		method.setRequestHeader("Accept", "*/*");
		// method.setRequestHeader("Accept-Encoding", "gzip, deflate");
		method.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
		method.setRequestHeader("Cache-Control", "no-cache");
		method.setRequestHeader("Connection", "keep-alive");
		method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		method.setRequestHeader("Cookie",
				"cnkiUserKey=292937db-cee9-2640-474c-8eff529e516e; UM_distinctid=1646477e5e167e-000db4ac18884b-5b193413-1fa400-1646477e5e2d64; Ecp_ClientId=3180806092301107320; Captcha=b650cff764fb8d1c; ASP.NET_SessionId=3bw1zfhidgpeofqe2aaexauf; TTKN.AAMS.V1.0.0=f297f55b6efb45cc8485932067285ada; .TTKNOA=58A8D11C3FEA31EC56D0B45B73B19B298D478E90C6F7EB335EA74D5DB712310EB49A01D848BCB3230530FB655F5AA615C9C279F99DFEE6F36DD30BA23FCE6A6B085C8000BA14522B4822957F51E622E5107E1690CF8EE4455D291D83F9A87FA083578A1D17CEEC498A3AFCF64D5FE4832DE1455D7628FDFA7AD0A51BF014657EAB15824F44C100DEECBC2B0143113845B88FCF3567A6A54D95A807AE285A146E014E5CC209E53845419FD0BA31C5F4EC9DE7C155EE7704DB8BE03B21C1237F4544759C0F6D462F9E51E23543663B1F8DD5B55D46608201AEAC7B9E19C1B2F0A0E81332A54049E5E1C39EC311435E9B4AD30D7A53F4C338FB90617660352639DB28FE17F41B6AACA2762E9AC5A1BEF8EC536D40D1676E00214A0643791138059B47C70D83179BF4B4A14DAC55964CAEC8394E97D7CD4FFB4B706A949A1B3F49D223B95F10AD9100C060033010EC12225413B55EC4A7D610E273E4A0C8F7E1B8A3");
		method.setRequestHeader("Host", "oa.cnki.net");
		method.setRequestHeader("Origin", "http://oa.cnki.net");
		method.setRequestHeader("Referer",
				"http://oa.cnki.net/web/TTKN/DailyWork/TouchManList_New.aspx?PageID=3&Height=917&Width=1920&UserID=5458&VisitID=6964813&Iden=328");
		method.setRequestHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
		method.setRequestHeader("X-MicrosoftAjax", "Delta=true");
		method.setRequestHeader("X-Requested-With", "XMLHttpRequest");

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
}
