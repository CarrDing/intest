package com.intest.test;


import com.intest.asn.DataParse;
import com.intest.mqtt.Server;

public class SendTerminalData {

	public static void main(String[] args) throws Exception {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int j = 2; j < 3; j++) {
					String topic = "/123456/asn1/1/" + j;
					send(topic,"server1", 1310000);
				}
			}
		}).start();
	
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				String topic = "/123456/asn1/2/";
				for (int j = 1; j < 11; j++) {
					topic += j;
					send(topic,"server2" + j);
				}
				
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				String topic = "/123456/asn1/3/";
				for (int j = 1; j < 11; j++) {
					topic += j;
					send(topic, "server3" + j);
				}
				
			}
		}).start();*/
		
	}
	
	public static void send(String topic, String clientId, Integer num) {
		Server server = new Server("tcp://101.132.39.198:1883", topic, clientId, "admin", "admin");
		try {
			long start = System.currentTimeMillis();
			for(int i = 0;i < num; i++) {
				server.publish(DataParse.TerminalData(i));
			}
			long end = System.currentTimeMillis();
			server.disConnect();
			System.out.println(end - start);
		} catch (Exception e) {	
			e.printStackTrace();
		}
	
	}
}
