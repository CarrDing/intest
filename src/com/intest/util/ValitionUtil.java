package com.intest.util;

import java.math.BigInteger;

public class ValitionUtil {

	//校验数据长度
    public static boolean valitionLength(byte[] length, int payload) {
    	if(Integer.parseInt(binary(length, 10)) < payload) {
    		return true;
    	} else {
    		return false;
    	}
    }
    //校验值
    public static boolean valitionValue(byte[] asn, byte[] valition) {
		byte first = asn[0];
    	for(int i = 1;i< asn.length;i++) {
    		first ^= asn[i]; 
    	}
    	if (first == valition[0]) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    //进制转换
    public static String binary(byte[] bytes, int radix){  
    	// 这里的1代表正数  
        return new BigInteger(1, bytes).toString(radix);
    } 
}
