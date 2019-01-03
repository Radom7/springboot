package com.haiyu.utils;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.lang.reflect.Field;
import java.util.*;

/**
 * @author 林尤庆
 * @date 2018年3月31日 下午4:31:31
 * @version 1.0
 */
public class Sign {

	private static final Logger L = LoggerFactory.getLogger(Sign.class);
	/**
	 * 签名算法
	 * @param o 要参与签名的数据对象
	 * @return 签名
	 * @throws IllegalAccessException
	 */
	public static String getSign(Object o) throws IllegalAccessException {
		ArrayList<String> list = new ArrayList<String>();
		Class cls = o.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			if (f.get(o) != null && f.get(o) != "") {
				String name = f.getName();
				XStreamAlias anno = f.getAnnotation(XStreamAlias.class);
				if(anno != null)
					name = anno.value();
				list.add(name + "=" + f.get(o) + "&");
			}
		}
		int size = list.size();
		String [] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < size; i ++) {
			sb.append(arrayToSort[i]);
		}
		String result = sb.toString();
		result += "key=" + Configure.getKey();
		System.out.println("签名数据："+result);
		result = MD5.MD5Encode(result).toUpperCase();
		return result;
	}

	public static String getSign(Map<String,Object> map){
		ArrayList<String> list = new ArrayList<String>();
		for(Map.Entry<String,Object> entry:map.entrySet()){
			if(entry.getValue()!=""){
				list.add(entry.getKey() + "=" + entry.getValue() + "&");
			}
		}
		int size = list.size();
		String [] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < size; i ++) {
			sb.append(arrayToSort[i]);
		}
		String result = sb.toString();
		result += "key=" + Configure.getKey();
		//Util.log("Sign Before MD5:" + result);
		result = MD5.MD5Encode(result).toUpperCase();
		//Util.log("Sign Result:" + result);
		return result;
	}

	public static String Sign(SortedMap<Object, Object> parameters, String Key) {

		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator<?> it = es.iterator();
		while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}

		sb.append("key=" + Key);

		String sign = MD5.MD5Encode(sb.toString(), "UTF-8").toUpperCase();

		return sign;

	}

}
