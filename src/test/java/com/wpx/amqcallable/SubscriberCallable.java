package com.wpx.amqcallable;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;
import java.util.concurrent.Callable;

/**
 * @Author: wpx
 * @Date: 2020/3/24 15:22
 * @Version: V_1.0.0
 */
public class SubscriberCallable  implements Callable {
    private final String broker_URL = "tcp://192.168.171.30:61616";
    private String topic_name;
    private  int count= 5;

    public SubscriberCallable(String topic_name) {
        this.topic_name = topic_name;
    }

    @Override
    public Object call() throws Exception {
        //1.创建发布发布的连接工厂
        TopicConnectionFactory connectionFactory = new ActiveMQConnectionFactory(broker_URL);

        //2.创建连接
        TopicConnection topicConnection = connectionFactory.createTopicConnection();
        topicConnection.start();

        //3.创建回话
        TopicSession topicSession = topicConnection.createTopicSession(true, Session.AUTO_ACKNOWLEDGE);
        Topic topic = new ActiveMQTopic("150-Topic");
        MessageConsumer consumer = topicSession.createConsumer(topic);
        while (count<=5){
            Message receive = consumer.receive();
            TextMessage textMessage = (TextMessage) receive;
            System.out.println(topic_name + ": " + textMessage.getText());
            count++;
        }


        return true;
    }
}
