package com.intest.service;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import com.intest.asn.CustNameReq;
import com.intest.asn.DataParse;
import com.intest.asn.UserData;
import com.intest.mqtt.Server;

public class ParamQueryService {
	
	public static void main(String[] args) {
		//query("intReq",true);
	}

	public static void querySingle(String queryType, Boolean isQuery) {
		try {
			Server server = new Server("tcp://101.132.39.198:1883","/123456/asn1/1/3", "server1", "admin", "admin");
			Map<String, Object> map = new HashMap<String, Object>();
			/*map.put("intReq", true);
			map.put("link1Req", true);
			map.put("link2Req", true);
			map.put("link2EnReq", true);
			map.put("dbcNameReq", true);
			map.put("sysReq", true);
			map.put("sdHzReq", true);
			map.put("sdCapReq", true);*/
			map.put(queryType, isQuery);
			server.publish(DataParse.paraQueryReq(map));
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("static-access")
	public static void queryCan(int start, int end) {
		Server server = new Server("tcp://101.132.39.198:1883","/123456/asn1/1/3", "server1", "admin", "admin");
		Map<String, Object> map = new HashMap<String, Object>();
		CustNameReq custNameReq = new CustNameReq();
		custNameReq.startNo = start;
		custNameReq.endNo = end;
		custNameReq.dbcMD5 = new byte[]{'3','A','9','0','7','D','D','5','0','D','E','9','8','6','D','C','F','F','4','2','D','3','1','E','8','F','5','D','E','F','4','C'};
		map.put("custNameReq", custNameReq);
		System.out.println(UserData.TYPE.toHexString(DataParse.paraQueryReq(map)));
		try {
			server.publish(DataParse.paraQueryReq(map));
		} catch (MqttPersistenceException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
}
