package com.intest.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.intest.queen.PayLoadQueen;
import com.intest.queen.RabbitmqReadPayLoadQueen;
import com.intest.threadpool.ThreadUtil;

public class Client {

    public MqttClient client;
    public MqttConnectOptions options;
    public MqttCallback mqttCallback = new MqttCallback(){
		@Override
		public void connectionLost(Throwable cause) {
			try {
				connect();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
    public String host;
    public String topic;
    public String clientid;

    public Client(String host, String topic, String clientid, String userName, String passWord) {
        try {
            // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(host, clientid, new MemoryPersistence());
            // MQTT的连接设置
            options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(false);
            // 设置连接的用户名
            options.setUserName(userName);
            // 设置连接的密码
            options.setPassword(passWord.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(60);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(60);
            // 设置回调
            client.setCallback(mqttCallback);
            //MqttTopic topic = client.getTopic(TOPIC);
            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
            //options.setWill(topic, "close".getBytes(), 2, true);
            client.connect(options);
            //订阅消息
            int[] Qos  = {2};
            String[] topic1 = {topic};
            client.subscribe(topic1, Qos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void connect() throws Exception {
    	MqttClient client = new MqttClient(host, clientid, new MemoryPersistence());
    	client.connect(options);
    	client.setCallback(mqttCallback);
    	int[] Qos  = {2};
        String[] topic1 = {topic};
        client.subscribe(topic1, Qos);
    }

    public static void main(String[] args) throws MqttException {
    	
    	//client 中有多少个终端号就会开启
    	new Client("tcp://101.132.39.198:1883","/123456/asn1/1/1", "client1", "admin", "admin");
    	//写入rabbitmq
    	new ThreadUtil().read(3, new RabbitmqReadPayLoadQueen());
        
        //开启n个向终端发送数据的线程
        //new ThreadUtil().read(1, new ReadPublishQueen());
    }
}
