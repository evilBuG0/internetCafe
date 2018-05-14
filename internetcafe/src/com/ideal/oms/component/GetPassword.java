package com.ideal.oms.component;

import org.apache.commons.codec.digest.DigestUtils;

import sun.security.provider.MD5;

public class GetPassword {
	public static void main(String[] args) {
		System.out.println(DigestUtils.md5Hex("admin123"));
	}
	
	
}
