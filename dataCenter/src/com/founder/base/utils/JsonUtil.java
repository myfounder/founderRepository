package com.founder.base.utils;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	public static String list2Json(List list) {
		JSONArray fromObject = JSONArray.fromObject(list);
		return fromObject.toString();
	}

	public static String object2Json(Object obj) {
		JSONObject fromObject = JSONObject.fromObject(obj);
		return fromObject.toString();
	}
}
