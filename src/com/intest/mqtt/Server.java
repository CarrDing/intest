package com.intest.mqtt;


import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.intest.queen.PayLoadQueen;

public class Server {
	
	private String host;
	private String topic;
    private String clientid;
    private MqttClient client;
    MqttCallback mqttCallback = new MqttCallback(){
		@Override
		public void connectionLost(Throwable cause) {
    		
		}

		@Override
		public void messageArrived(String topic, MqttMessage message) throws Exception {
    		byte[] payload = message.getPayload();
            System.out.println("接收消息内容 : " + payload);
            PayLoadQueen.put(payload);
		}

		@Override
		public void deliveryComplete(IMqttDeliveryToken token) {
			System.out.println("deliveryComplete---------" + token.isComplete());
		}
    	
    };

    public Server(String host, String topic, String clientid, String userName, String passWord) {
    	this.host = host;
    	this.clientid = clientid;
    	this.topic = topic;
        //MemoryPersistence设置clientid的保存形式，默认为以内存保存
        try {
			client = new MqttClient(this.host, this.clientid, new MemoryPersistence());
		} catch (MqttException e1) {
			e1.printStackTrace();
		}
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(60);
        // 设置会话心跳时间
        options.setKeepAliveInterval(60);
        try {
            client.setCallback(mqttCallback);
            client.connect(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disConnect() {
    	try {
			client.disconnect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
    }
    
    public void publish(MqttTopic topic , MqttMessage message) throws MqttPersistenceException,
            MqttException {
        MqttDeliveryToken token = topic.publish(message);
        token.waitForCompletion();
    }

	public void publish(byte[] respon) throws MqttException, MqttPersistenceException {
		MqttMessage message = new MqttMessage();
        message.setQos(2);
        message.setRetained(false);
        message.setPayload(respon);
        MqttTopic mqttTopic = client.getTopic(topic);
        publish(mqttTopic , message);
	}
}