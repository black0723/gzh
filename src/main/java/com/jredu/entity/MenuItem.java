package com.jredu.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MenuItem {
	public static Map<String, Object> getMenuItem() {
		Map<String, Object> mapMenu1 = new LinkedHashMap<>();
		
		List<Map<String, Object>> list1 =new ArrayList<>();
		Map<String, Object> mapMenu2_1 = new LinkedHashMap<>();
		mapMenu2_1.put("type", "click");
		mapMenu2_1.put("name", "oneday");
		mapMenu2_1.put("key", "V1001_TODAY_MUSIC");
		list1.add(mapMenu2_1);
		
		
		Map<String, Object> mapMenu2_2 = new LinkedHashMap<>();
		mapMenu2_2.put("name", "菜单");
		
		List<Map<String, Object>> list2 =new ArrayList<>();
		Map<String, Object> mapMenu3_1 = new LinkedHashMap<>();
		mapMenu3_1.put("type", "view");
		mapMenu3_1.put("name", "搜索");
		mapMenu3_1.put("url", "http://www.soso.com/");
		
		Map<String, Object> mapMenu3_2 = new LinkedHashMap<>();
		mapMenu3_2.put("type", "click");
		mapMenu3_2.put("name", "赞一下我们");
		mapMenu3_2.put("key", "V1001_GOOD");
		
		list2.add(mapMenu3_1);
		list2.add(mapMenu3_2);
		
		mapMenu2_2.put("sub_button", list2);
		
		list1.add(mapMenu2_2);
		
		mapMenu1.put("button", list1);
		
		return mapMenu1;
	}
}
