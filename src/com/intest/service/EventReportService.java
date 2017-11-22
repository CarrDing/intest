package com.intest.service;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import com.intest.asn.DataParse;
import com.intest.mqtt.Server;

public class EventReportService {

	public static void main(String[] args) {
		//report();
	}

	public static void report(String type) {
		Server server = new Server("tcp://101.132.39.198:1883","/123456/asn1/1/3", "server1", "admin", "admin");
		Map<String, Object> map = new HashMap<String, Object>();
		switch (type) {
		case "state":{map.put("state", true);}break;
		case "vf1939":{map.put("state", true);}break;
		case "vfCustm":{map.put("state", true);}break;
		default:break;
		}
		try {
			server.publish(DataParse.eventReport(map));
		} catch (MqttPersistenceException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
}
