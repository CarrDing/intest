package com.intest.hbase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import com.intest.asn.DataParse;
import com.intest.asn.UserData;
import com.intest.bean.DataWareHouseV1;
import com.intest.util.BuildRowKeyUtil;
import com.intest.util.BuildSeqId;
import com.intest.util.PropertiesUtil;

import net.sf.json.JSONObject;



public class HbaseUtil {
	
	public static PropertiesUtil propertiesUtil = new PropertiesUtil();
	private static List<Put> list = new ArrayList<Put>();
			
	public static void create(String table){
		Configuration conf = HBaseConfiguration.create();
		HBaseAdmin hAdmin =null;
	    try {           
	    	hAdmin  = new HBaseAdmin(conf);
	        HTableDescriptor hTableDesc = new HTableDescriptor(
	                TableName.valueOf(table));
	        hTableDesc.addFamily(new HColumnDescriptor("cf:z"));
	        hAdmin.createTable(hTableDesc);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }finally{
	    	try {
	    		if(hAdmin != null) {
	    			hAdmin.close();
	    		}
			} catch (Exception e2) {
				throw new RuntimeException(e2);
			}
	    }
	}
	
	public static void puts(List<Put> list, String tableName){
		Configuration configuration = HBaseConfiguration.create();
	    configuration.set("hbase.zookeeper.quorum", "dataNode01,dataNode02,dataNode03,dataNode04");
		configuration.set("hbase.rootdir", "hdfs://nameNode/hbase");
		HTable hTable =null;
		try {
			hTable = new HTable(configuration, tableName);
			hTable.setAutoFlushTo(false);
			hTable.setWriteBufferSize(8*1024*1024);
			hTable.put(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
			try {
				hTable.close();
			} catch (Exception e2) {
				throw new RuntimeException(e2);
			}
		}
	}
	
	public static  Boolean put(String message) {

		if(!message.equals("null")) {
			try {
				UserData userData = DataParse.decode(message);
    			// 更新历史库
    			if (userData.chioceDate.dataReport.vehiData.vehiReport != null) {
    				List<Double> canValue = userData.chioceDate.dataReport.vehiData.vehiReport;
    				
    				DataWareHouseV1 dataWareHouseV1 = new DataWareHouseV1();
    				dataWareHouseV1.setTc(201704221);
    				dataWareHouseV1.setSeqId(
    						BuildSeqId.getSeqId(new Date(userData.basicInfo.timeSec * 1000 + userData.basicInfo.timeMsec)));
    				dataWareHouseV1.setMd5("1F15AB7B9BCBEA5D54883863A2FFAD8C");
    				dataWareHouseV1.setTe(new Date(userData.basicInfo.timeMsec + userData.basicInfo.timeSec * 1000));
    				dataWareHouseV1.setLongitude(
    						userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.longitude);
    				dataWareHouseV1.setLatitude(
    						userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.latitude);
    				dataWareHouseV1.setAltitude(
    						userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.height);
    				dataWareHouseV1.setSpeed(
    						userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.speed);
    				dataWareHouseV1.setMileage(
    						userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.distance);
    				dataWareHouseV1.setDirection(
    						userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.direction);
    				dataWareHouseV1.setVeliData(canValue);
    				JSONObject jsonObject = JSONObject.fromObject(dataWareHouseV1);
    				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    				String rowkey = BuildRowKeyUtil.BuildRowKey(formatter.format(dataWareHouseV1.getTe()), "201704221");
    				Put put = new Put(Bytes.toBytes(rowkey));
    				put.add(Bytes.toBytes("cf"), Bytes.toBytes("z"), Bytes.toBytes(jsonObject.toString()));
    				list.add(put);
    				if(list.size() > 5000) {
    					long start = System.currentTimeMillis();
    					puts(list, "ota");
    					long end = System.currentTimeMillis();
    					System.out.println("更新hbase历史库用时:" + (end - start) / 1000);
    					list.clear();
    					
    				}
    			}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else {
			if (list.size() > 0 ) {
				long start = System.currentTimeMillis();
				puts(list, "ota");
				list.clear();
				long end = System.currentTimeMillis();
				System.out.println("更新hbase历史库用时:" + (end - start) / 1000);
			}
		}
	
		return true;
	}
}