package org.ek.sample.domain;

import java.util.Map;

public class StringConverter {
	
	/**
	 * 引数を大文字にして返却する。
	 * @param payload
	 * @return Changed Upper Cased input value
	 */
	public String upperCase(String payload) {
		return payload.toUpperCase();
	}
	
	public String upperCase(Map<String, Object> payload) {
		return ((String) payload.get("addedHeader")).toUpperCase();
	}

}
