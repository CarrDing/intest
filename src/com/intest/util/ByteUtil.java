package com.intest.util;

public class ByteUtil {

	/**
	 * int类型转换成byte[]数组
	 * @param a
	 * @return
	 */
	public static byte[] int2Bytes(int a) {  
		  
        byte[] b = new byte[4];  
        b[0] = (byte) (a >> 24);  
        b[1] = (byte) (a >> 16);  
        b[2] = (byte) (a >> 8);  
        b[3] = (byte) (a);  
  
        return b;  
    }
	
	public static double bytes2Double(byte[] arr) {  
        long value = 0;
        for (int i = 0; i < 8; i++) {  
            value |= ((long) (arr[i] & 0xff)) << (8 * i);  
        }  
        return Double.longBitsToDouble(value);  
    } 
	
	public static byte[] getZlib(byte[] payLoad) {
		byte[] zlib = new byte[1];
        System.arraycopy(payLoad, 48, zlib, 0, 1);
		return zlib;
	}
	
	public static byte[] getDataLength(byte[] payLoad) {
		byte[] dataLength = new byte[4];
        System.arraycopy(payLoad, 50, dataLength, 0, 4);
		return dataLength;
	}
	
	public static byte[] getValitionValue(byte[] payLoad) {
		byte[] valitionValue = new byte[1];
        System.arraycopy(payLoad, 54, valitionValue, 0, 1);
		return valitionValue;
	}
	
	public static byte[] getASN1Name(byte[] payLoad) {
		byte[] asn1Name = new byte[32];
        System.arraycopy(payLoad, 0, asn1Name, 0, 32);
		return asn1Name;
	}
	
	public static byte[] getMD5(byte[] payLoad) {
		byte[] md5 = new byte[32];
        System.arraycopy(payLoad, 32, md5, 0, 16);
		return md5;
	}
}
