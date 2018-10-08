package com.jredu.util;

import java.io.File;

public class Constants {
	public static final String appID = "wx0d406e31c0a9944e";
	public static final String appsecret = "ad5a6c9e953d00d7f1e0a13f1da96ceb";
	// 开发者自行定义Tooken
	public static final String tooken = "bysj1234";
	//获取access_token的URL前缀
	public static final String tokenUrlPrefix = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	//accessToken.txt保存路径
	public static final String accessTokenPath =FileUtil.getAppPath(Constants.class) + File.separator + "accessToken.txt";
}
