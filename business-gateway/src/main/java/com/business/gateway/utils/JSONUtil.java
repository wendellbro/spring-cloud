package com.business.gateway.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONUtil {

	public static String toJSONString(Object object){
		return JSONObject.toJSONString(object, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty);
	}
	
	public static <T> T parseObject(String text, Class<T> clazz){
		return JSONObject.parseObject(text, clazz);
	}
	
	public static <T> T toJavaObject(JSONObject jsonObject, Class<T> clazz){
		return JSONObject.toJavaObject(jsonObject, clazz);
	}

}
