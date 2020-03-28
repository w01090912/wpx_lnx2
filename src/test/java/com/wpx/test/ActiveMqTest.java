package com.wpx.test;

import com.wpx.WpxLnx2Application;
import com.wpx.amqcallable.ReceiverCallable;
import com.wpx.amqcallable.SubscriberCallable;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.*;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Author: wpx
 * @Date: 2020/3/23 15:35
 * @Version: V_1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WpxLnx2Application.class)
public class ActiveMqTest {

    /**
     *
     *
     *
     * @Author: wpx
     * @Description: P2P消息是生产者
     * @Date: 2020/3/24 
     * @param 
     */
    @Test
    public void testSender()throws JMSException{
        //1.创建链接工厂
        String brokerURL = "tcp://192.168.171.30:61616";
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        //2.创建连接
        Connection connection = connectionFactory.createConnection();
        //3.创建回话    //第一个参数 是否开启事务   第二个参数开启自动回执
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        //4.创建生产者
        Destination destination = new ActiveMQQueue("152-queue1");
        MessageProducer producer = session.createProducer(destination);
        //5.创建消息
        TextMessage textMessage = session.createTextMessage("hello-152");
        //6.生产者发布消息
        producer.send(textMessage);
        //7.提交
        session.commit();
        //8释放资源
        connection.close();
        producer.close();
        session.close();
    }


    /**
     *
     *
     *
     * @Author: wpx
     * @Description: P2P消息消费者
     * @Date: 2020/3/24 
     * @param 
     */
    @Test
    public void testReceiver(){
        ReceiverCallable callable = new ReceiverCallable("152-queue1");
        FutureTask<Boolean> future=new FutureTask<Boolean>(callable);
        Thread thread = new Thread(future);
        thread.start();
        try{
            if (future.get()){}
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     *
     * @Author: wpx
     * @Description: 订阅生产者
     * @Date: 2020/3/24
     * @param
     */
    @Test
    public void testPublisher() throws JMSException {
        //1.创建发布发布的连接工厂
        String brokerURL = "tcp://192.168.171.30:61616";
        TopicConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);

        //2.创建连接
        TopicConnection topicConnection = connectionFactory.createTopicConnection();

        //3.创建回话
        TopicSession topicSession = topicConnection.createTopicSession(true, Session.AUTO_ACKNOWLEDGE);

        //4.创建发布
        Topic topic = new ActiveMQTopic("150-Topic");
        TopicPublisher publisher = topicSession.createPublisher(topic);

        //5.创建主题
        TextMessage textMessage = topicSession.createTextMessage("Hello-TestTopic");

        //6.发布
        publisher.send(textMessage);

        //7.提交
        topicSession.commit();

        //8.释放
        topicConnection.close();
        topicSession.close();
        publisher.close();
    }
    /**
     *
     * @Author: wpx
     * @Description:
     * @Date: 2020/3/24
     * @param
     */
    @Test
    public void testProduct()  {
        SubscriberCallable subscriberCallable = new SubscriberCallable("150-Topic");
        FutureTask<Boolean> futureTask = new FutureTask<Boolean>(subscriberCallable);
        Thread thread = new Thread(futureTask);
        thread.start();
        try{
            if (futureTask.get()){}
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
