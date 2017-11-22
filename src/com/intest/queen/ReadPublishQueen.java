package com.intest.queen;

import com.intest.mqtt.Server;

public class ReadPublishQueen implements Runnable{

	@Override
	public void run() {
		while(true) {
			try {
				byte[] bs = PublishQueen.take();
				Server server = new Server("tcp://101.132.39.198:1883","/123456/asn1/1/3", "server1", "admin", "admin");
				server.publish(bs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
