package com.intest.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.intest.constant.Constant;

public class BuildSeqId {

	public static String getSeqId(Date te) { 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS.");
		String time = formatter.format(te);
		String rowKey = "";
		for (Integer i = 0; i < time.length(); i++) {	
			if (time.charAt(i) - Constant.ZERO <= 9 && time.charAt(i) - Constant.ZERO >= 0) {
				rowKey += time.charAt(i);
			}
		}
		//System.out.println("seqId:" + rowKey + "...");
		return rowKey/*.substring(0, rowKey.length() - 1)*/; 
	}
	
	public static void main(String[] args) {
		System.out.println(getSeqId(new Date()));
	}
}
