package com.jredu.util;

import java.io.File;

public class Constants {
	public static final String appID = "wx0d406e31c0a9944e";
	public static final String appsecret = "ad5a6c9e953d00d7f1e0a13f1da96ceb";
	// 开发者自行定义Tooken
	public static final String tooken = "bysj1234";
	//微信URL前缀
	public static final String URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/";
	
	//获取access_token的URL前缀
	public static final String tokenUrlPrefix = URL_PREFIX + "token?grant_type=client_credential";
	//accessToken.txt保存路径
	public static final String accessTokenPath = FileUtil.getAppPath(Constants.class) + File.separator + "accessToken.txt";
	
	//创建自定义菜单的URL
	public static final String MENU_CREATE_URL = URL_PREFIX + "menu/create?";
	//删除自定义菜单的URL
	public static final String MENU_DELETE_URL = URL_PREFIX + "menu/delete?";
}
