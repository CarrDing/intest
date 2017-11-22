package com.intest.rabbitmq;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

import com.intest.constant.Constant;
import com.intest.exception.CustomException;
import com.intest.hbase.HbaseUtil;
import com.intest.util.PropertiesUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;

public class RabbitMqUtil {
	private  ConnectionFactory connectionFactory = null;
	private  Channel channel = null;
	private  Connection connection = null;
	private  QueueingConsumer consumer = null;
	
	public RabbitMqUtil() {
		connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(PropertiesUtil.getProperties(Constant.IP_HOST));
		connectionFactory.setUsername(PropertiesUtil.getProperties(Constant.USER_NAME));
		connectionFactory.setPassword(PropertiesUtil.getProperties(Constant.PASS_WORD));
		connectionFactory.setVirtualHost(PropertiesUtil.getProperties(Constant.VIRTUAL_HOST));
		//获取连接
		try {
			connection = connectionFactory.newConnection();
			//获取通道
			channel = connection.createChannel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void receive() throws Exception{
		// 设置自动恢复  
		ExecutorService service = Executors.newFixedThreadPool(10);  
		connectionFactory.setSharedExecutor(service); 
		connectionFactory.setAutomaticRecoveryEnabled(true);  
		connectionFactory.setNetworkRecoveryInterval(5000);// 设置 没10s ，重试一次  
		connectionFactory.setTopologyRecoveryEnabled(false);// 设置不重新声明交换器，队列等信息。
		//设置通道的Qos
		channel.basicQos(0,1,false);
		//声明队列
		channel.queueDeclare(PropertiesUtil.getProperties(Constant.QUEEN_NAME), false, false, false, null);
		//channel.queueBind(PropertiesUtil.getProperties(Constant.QUEEN_NAME), PropertiesUtil.getProperties(Constant.EXCHANGE_NAME), PropertiesUtil.getProperties(Constant.ROUTING_KEY));
		//创建消费者
		consumer = new QueueingConsumer(channel);
		//绑定通道
		channel.basicConsume(PropertiesUtil.getProperties(Constant.QUEEN_NAME), false, consumer);
		Delivery delivery = null;
		boolean isBroken = false;
		while (true) {
			/* 获取消息 */
			if (isBroken) {
				System.out.println(" [x] 开始接收数据可能会卡 ----" + channel.isOpen());
			}
			try {
				if (channel.isOpen()) {
					delivery = consumer.nextDelivery(1000);
					if (delivery != null) { 
						byte[] body = delivery.getBody();
						String message = new String(body, "utf-8");
						System.out.println(message);
						/*new ReadPayLoad2UserData2Oracle().getMessageFromRmq2OracleJDBC(message);
						HbaseUtil.put(message);*/
						channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
					} else {
						HbaseUtil.put("null");
						new ReadPayLoad2UserData2Oracle().getMessageFromRmq2OracleJDBC("null");
					}
				} 
			} catch (ShutdownSignalException e) {
				try {
					if (channel.isOpen()) {
						consumer = new QueueingConsumer(channel);
						channel.basicConsume(PropertiesUtil.getProperties(Constant.QUEEN_NAME), false, consumer);
						isBroken = false;
						System.out.println(" [x] 恢复成功 ----");
					}
				} catch (Exception e2) {
					isBroken = true;
					e2.printStackTrace();
					System.out.println(" [x] 接收线程--- ShutdownSignalException-Exception");
				}
			} catch (ConsumerCancelledException e) {
				throw new CustomException("10003", "Consumer had cancelled");
			} catch (InterruptedException e) {
				throw new CustomException("10004", "have interrupted");
			}
		}
	}
	
	public void send(byte[] message) {
		try {
			//设置通道的Qos
			channel.basicQos(0,1,false);
			//声明队列
			channel.queueDeclare(PropertiesUtil.getProperties(Constant.QUEEN_NAME), false, false, false, null);
			channel.basicPublish("", "Carr", null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		RabbitMqUtil rabbitMqUtil = new RabbitMqUtil();
		/*for (int i = 0; i < 100000; i++) {
			rabbitMqUtil.send(("this is message " + i).getBytes());
		}*/
		rabbitMqUtil.receive();
	}
}
