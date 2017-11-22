package com.intest.util;

public class ValitionValue {

	//校验值
    public static byte valitionValue(byte[] asn) {
		byte first = asn[0];
    	for(int i = 1;i< asn.length;i++) {
    		first ^= asn[i]; 
    	}
    	return first;
    }
}
