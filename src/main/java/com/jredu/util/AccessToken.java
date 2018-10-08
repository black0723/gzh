package com.jredu.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 获取access_token https请求方式: GET
 * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
 * 
 * @author Administrator
 *
 *         参数 是否必须 说明 grant_type 是 获取access_token填写client_credential appid 是
 *         第三方用户唯一凭证 secret 是 第三方用户唯一凭证密钥，即appsecret
 * 
 *         正常情况下，微信会返回下述JSON数据包给公众号：
 *         {"access_token":"ACCESS_TOKEN","expires_in":7200}
 */
public class AccessToken {

	/**
	 * 用来获取没有过期的access_token
	 */
	public static String fetchAccessToken() {
		// 读取本地文件（readAccessToken）
		Map<String, Object> data = readAccessToken();
		// - 本地有文件
		if (data != null) {
			// - 判断它是否过期(isValidAccessToken)
			// - 过期了
			if (!isValidAccessToken(data)) {
				// - 重新请求获取access_token(getAccessToken)，
				String accessToken = getAccessToken();
				// 保存下来覆盖之前的文件（保证文件是唯一的）(saveAccessToken)
				saveAccessToken(accessToken);
				return accessToken;
			} else {
				// - 没有过期
				// - 直接使用
				return JSON.toJSONString(data);
			}
		} else {
			// - 本地没有文件
			// - 发送请求获取access_token(getAccessToken)，
			String accessToken = getAccessToken();
			// 保存下来（本地文件）(saveAccessToken)，直接使用
			saveAccessToken(accessToken);
			return accessToken;
		}
	}

	/**
	 * 1.发送请求
	 * 
	 * @return
	 */
	public static String getAccessToken() {
		// 定义请求的地址
		String url = Constants.tokenUrlPrefix + "&appid=" + Constants.appID + "&secret=" + Constants.appsecret;
		// 发送请求
		// {"access_token":"ACCESS_TOKEN","expires_in":7200}
		String accessToken = HttpUtil.doGet(url, "UTF-8");
		JSONObject object = JSON.parseObject(accessToken);
		long expires_in = Long.parseLong(object.get("expires_in").toString());
		// 设置access_token的过期时间，5分钟之前
		expires_in = System.currentTimeMillis() + (expires_in - 300) * 1000;
		object.put("expires_in", expires_in);
		return JSON.toJSONString(object);
	}

	/**
	 * 2.保存文件
	 * 
	 * @param accessToken
	 */
	public static void saveAccessToken(String accessToken) {
		FileUtil.writeStringToFile(Constants.accessTokenPath, accessToken);
	}

	/**
	 * 3.读取文件
	 */
	public static Map<String, Object> readAccessToken() {
		String accessToken = FileUtil.readToEnd(Constants.accessTokenPath, "UTF-8");
		if (accessToken == null)
			return null;
		return JSON.parseObject(accessToken);
	}

	/**
	 * 4.用来检测access_token是否有效的
	 */
	public static boolean isValidAccessToken(Map<String, Object> data) {
		// 检测传入的参数是否是有效的
		if (data == null && data.get("access_token") == null && data.get("expires_in") == null) {
			// 代表access_token无效的
			return false;
		}

		// 检测access_token是否在有效期内
		/*
		 * if (data.expires_in < Date.now()) { //过期了 return false; } else { //没有过期
		 * return true; }
		 */
		long expires_in = Long.parseLong(data.get("expires_in").toString());
		return expires_in > System.currentTimeMillis();
	}
}
