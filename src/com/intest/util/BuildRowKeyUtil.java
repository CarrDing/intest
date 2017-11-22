package com.intest.util;


/** 
* 
* 项目名称：dw 
* 类名称：BuildRowKeyUtil 
* 类描述： 生成hbase的rowkey的工具类
* 创建人：carr 
* 创建时间：2017年6月14日 上午10:23:24 
* 修改人：carr 
* 修改时间：2017年6月14日 上午10:23:24 
* 修改备注： 
* @version 
* 
*/
public class BuildRowKeyUtil {

	public static String BuildRowKey(String time,String tc) { 
		String rowKey = "";
		for (int i = 0; i < time.length(); i++) {	
			if (time.charAt(i) - '0' <= 9 && time.charAt(i) - '0' >= 0) {
				rowKey += time.charAt(i);
			}
		}
		return "000000000" + tc + rowKey;
	}
}
