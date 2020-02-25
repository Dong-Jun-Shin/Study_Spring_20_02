package com.spring.injection;

import java.util.Map;
import java.util.Properties;

public class CollectionBean {
//	// List 타입
//	private List<String> addressList;
//	
//	public void setAddressList(List<String> addressList) {
//		this.addressList = addressList;
//	}
//	
//	public List<String> getAddressList(){
//		return addressList;
//	}
	
//	// Set 타입
//	private Set<String> addressList;
//
//	public Set<String> getAddressList() {
//		return addressList;
//	}
//
//	public void setAddressList(Set<String> addressList) {
//		this.addressList = addressList;
//	}
	
	// Map 타입
	private Properties addressList;

	public Properties getAddressList() {
		return addressList;
	}

	public void setAddressList(Properties addressList) {
		this.addressList = addressList;
	}
	
	
}
