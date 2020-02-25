package com.spring.injection;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionBeanClient {
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		CollectionBean bean = (CollectionBean)factory.getBean("collectionBean");
		
//		List<String> list = bean.getAddressList();
//		for (String str : list) {
//			System.out.println(str);
//			System.out.println(str.toString());
//		}
		
//		Set<String> addressList = bean.getAddressList();
//		Iterator<String> it = addressList.iterator();
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
		
//		Map<String, String> addressList = bean.getAddressList();
//		// values 메서드 활용
//		for (String str : addressList.values()) {
//			System.out.println(str);
//		}
//		
//		// Entry 활용
//		for (Map.Entry<String, String> addList : addressList.entrySet()) {
//			String key = addList.getKey();
//			String value = addList.getValue();
//			System.out.println("key=" + key + ", value=" + value);
//		}
		
		Properties addressList = bean.getAddressList();
		for (Entry<Object, Object> addList : addressList.entrySet()) {
			String key = (String)addList.getKey();
			String value = (String)addList.getValue();
			System.out.println("key=" + key + ", value=" + value);
		}
		
		factory.close();
		
	}
}
