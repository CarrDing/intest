package com.intest.service;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import com.intest.asn.DataParse;
import com.intest.asn.IntRsp;
import com.intest.asn.LinkRsp;
import com.intest.mqtt.Server;

public class ParamSetService {

	public static void main(String[] args) throws Exception{
		//set()
	}

	public static void set(String type, String[] args) throws MqttException, MqttPersistenceException {
		Server server = new Server("tcp://101.132.39.198:1883","/123456/asn1/1/3", "server1", "admin", "admin");
		Map<String, Object> map = new HashMap<String, Object>();
		switch (type) {
		case "intSet":{
			//1.心跳和数据间隔设置
			IntRsp intRsp = new IntRsp();
			intRsp.dataInterval = Integer.parseInt(args[0]);//100
			intRsp.heartInterval = Integer.parseInt(args[1]);
			map.put("intSet", intRsp);
		}break;
		case "link1Set":{
			//2.主站IP和端口设置
			LinkRsp linkRsp = new LinkRsp();
			linkRsp.commPort = Integer.parseInt(args[0]);
			linkRsp.commUrl = args[1].getBytes();
			map.put("link1Set", linkRsp);
		}break;
		case "link2Set":{
			//3.第二链路的IP端口设置
			LinkRsp linkRsp2 = new LinkRsp();
			linkRsp2.commPort = Integer.parseInt(args[0]);
			linkRsp2.commUrl = args[1].getBytes();
			map.put("link2Set", linkRsp2);
		}break;
		case "link2EnSet":{
			//4.第二链路的开关设置
			map.put("link2EnSet", true);
		}break;
		case "dbcNameSet":{
			//5.dbc名称设置
			map.put("dbcNameSet", args[0].getBytes());
		}break;
		case "sysSet":{
			//6.系统休眠模式设置
			map.put("sysSet", Integer.parseInt(args[0]));
		}break;
		case "sdHzSet":{
			//7.sdhz
			map.put("sdHzSet", Integer.parseInt(args[0]));
		}break;
		default:
			break;
		}
		server.publish(DataParse.paraSetReq(map));
	}
}
