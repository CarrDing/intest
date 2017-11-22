package com.intest.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import com.intest.asn.DBCDownReq;
import com.intest.asn.DataParse;
import com.intest.asn.ImgDownReq;
import com.intest.mqtt.Server;

public class FileTransService {

	public static void main(String[] args) throws Exception, MqttException {
		//upload("img");
	
	}

	public static void upload(String type, String filePath) throws FileNotFoundException, IOException, MqttException, MqttPersistenceException {
		Server server = new Server("tcp://101.132.39.198:1883","/123456/asn1/1/3", "server1", "admin", "admin");
		Map<String, Object> map = new HashMap<String, Object>();
		//"D:\\image2.pkg"
		File file = new File(filePath);
		int bufferSize = 1024;
		long dataLength = file.length()/bufferSize;
		int totalLength = 1;
		if (file.length() % bufferSize == 0) {
			 totalLength = (int) (dataLength);
		} else {
			totalLength = (int) (dataLength) + 1;
		}
		FileInputStream in = new FileInputStream(file);
		byte buffer[] = new byte[bufferSize];
		int len = 0;
		int index = 1;
		switch (type) {
		case "img":
			while((len=in.read(buffer))>0){
				ImgDownReq imgDownReq = new ImgDownReq();
				imgDownReq.total = totalLength;
				imgDownReq.index = index;
				imgDownReq.length = len;
				if (len < bufferSize) {
					byte[] b = new byte[len];
					System.arraycopy(buffer, 0, b, 0, len);
					imgDownReq.data = b;
				} else {
					imgDownReq.data = buffer;
				}
				map.put("img", imgDownReq);
				server.publish(DataParse.fileTrans(map));
				index ++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			in.close();
			System.out.println("镜像文件发送完毕...");
			break;
		case "dbc":
			while((len=in.read(buffer))>0){
				if(len < 1024) { 
					DBCDownReq dbcDownReq = new DBCDownReq();
					dbcDownReq.data = buffer;
					dbcDownReq.index = index;
					dbcDownReq.length = len;
					dbcDownReq.total = totalLength;
					dbcDownReq.name = new byte[]{'/','t','e','s','t','(','3','0',')','.','d','b','c'};
					map.put("dbc", dbcDownReq);
					server.publish(DataParse.fileTrans(map));
					index ++;
				} else {
					DBCDownReq dbcDownReq = new DBCDownReq();
					dbcDownReq.data = buffer;
					dbcDownReq.index = index;
					dbcDownReq.length = len;
					dbcDownReq.total = totalLength;
					map.put("dbc", dbcDownReq);
					server.publish(DataParse.fileTrans(map));
					index ++;
				}
				
			}
			in.close();
			break;
		default:
			break;
		}	
	}
	
	public static int returnActualLength(byte[] data) { 
        int i = 0; 
        for (; i < data.length; i++) { 
            if (data[i] == '\0') 
                break; 
        } 
        return i; 
    }

}
