package com.intest.kafka;

import java.util.Properties;



import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MyProducer {
    public void send(String message) {
    	 Properties properties = new Properties();
    	 properties.put("bootstrap.servers", "192.168.2.111:9092,192.168.2.112:9092,192.168.2.113:9092,192.168.2.114:9092");
         properties.put("zookeeper.connect", "192.168.2.113:2181,192.168.2.111:2181,192.168.2.112:2181,192.168.2.114:2181");//声明zk  
         //properties.put("serializer.class", StringEncoder.class.getName());  
         properties.put("metadata.broker.list", "192.168.2.113:2181,192.168.2.111:2181,192.168.2.112:2181,192.168.2.114:2181");
         //The "all" setting we have specified will result in blocking on the full commit of the record, the slowest but most durable setting.
        //“所有”设置将导致记录的完整提交阻塞，最慢的，但最持久的设置。
         properties.put("acks", "all");
         //如果请求失败，生产者也会自动重试，即使设置成０ the producer can automatically retry.
         properties.put("retries", 0);

         //The producer maintains buffers of unsent records for each partition. 
         properties.put("batch.size", 16384);
         //默认立即发送，这里这是延时毫秒数
         properties.put("linger.ms", 1);
         //生产者缓冲大小，当缓冲区耗尽后，额外的发送调用将被阻塞。时间超过max.block.ms将抛出TimeoutException
         properties.put("buffer.memory", 33554432);
         //The key.serializer and value.serializer instruct how to turn the key and value objects the user provides with their ProducerRecord into bytes.
         properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
         properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

         //创建kafka的生产者类
         Producer<String, String> producer = new KafkaProducer<String, String>(properties);
         //生产者的主要方法
         // close();//Close this producer.
         //   close(long timeout, TimeUnit timeUnit); //This method waits up to timeout for the producer to complete the sending of all incomplete requests.
         //  flush() ;所有缓存记录被立刻发送
         producer.send(new ProducerRecord<String, String>("foo", message, message));
         producer.close();
    }
}
