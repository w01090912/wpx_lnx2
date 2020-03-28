package com.wpx.amqcallable;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;
import java.util.concurrent.Callable;

/**
 * @Author: wpx
 * @Date: 2020/3/24 17:44
 * @Version: V_1.0.0
 */
public class ReceiverCallable implements Callable {

    private final String broker_URL = "tcp://192.168.171.30:61616";
    private String queue_name;
    private  int count = 5;

    public ReceiverCallable(String queue_name) {
        this.queue_name = queue_name;
    }

    @Override
    public Object call() throws Exception {
        //1.创建链接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(broker_URL);

        //2.创建连接
        Connection connection = connectionFactory.createConnection();
        //注意:
        connection.start();

        //3.创建回话    //第一个参数 是否开启事务   第二个参数开启自动回执
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        //4.创建消费者
        Destination destination = new ActiveMQQueue("152-queue1");
        MessageConsumer consumer = session.createConsumer(destination);

        //5.消费
        TextMessage message = (TextMessage) consumer.receive();
        System.out.println(message.getText());
        Message receive = consumer.receive(1);

        //6.事务提交
        session.commit();

        //7.资源释放
        connection.close();
        consumer.close();
        session.close();
        return true;
    }
}
