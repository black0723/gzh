package com.jredu.util;

public class WeChatUtil {

	public static void main(String[] args) {
		String fileName = WeChatUtil.class.getClassLoader().getResource("menu.json").getPath();
		System.out.println(fileName);
		String menu = FileUtil.readToEnd(fileName, "UTF-8");
		// String menu =
		// "{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.soso.com/\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]}";
		// String menu = JSONObject.toJSONString(MenuItem.getMenuItem());

		System.out.println(menu);
		// 获取access_token
		String accessToken = (String) AccessToken.fetchAccessToken2().get("access_token");
		String url = Constants.MENU_CREATE_URL + "access_token=" + accessToken;
		System.out.println(url);
		String resutl = HttpUtil.doPost2(url, menu);
		System.out.println(resutl);
	}
}
