package com.intest.service;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.paho.client.mqttv3.MqttException;

import com.intest.asn.DataParse;
import com.intest.mqtt.Server;

public class RemoteControlService {

	public static void main(String[] args) {
		//control();
	}

	public static void control(String type, String[] args) throws Exception, MqttException {
		Server server = new Server("tcp://101.132.39.198:1883","/123456/asn1/1/3", "server1", "admin", "admin");
		Map<String, Object> map = new HashMap<String, Object>();
		switch (type) {
		case "reportReq":{map.put("reportReq", args[0]);}break;
		case "rebootReq":{map.put("rebootReq", args[0]);}break;
		case "resetDistReq":{map.put("resetDistReq", args[0]);}break;
		case "calibrateReq":{map.put("calibrateReq", Long.parseLong(args[0]));}break;
		case "vCanCardReq":{map.put("vCanCardReq", args[0]);}break;
		case "accumVarReq":{map.put("accumVarReq", Integer.parseInt(args[0]));}break;
		case "delFileReq":{map.put("delFileReq", args[0].getBytes());}break;
		default:break;
		}
		server.publish(DataParse.rmtControl(map));
	}
}
