package com;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @Author ll
 * @Date 2018/11/26 17:06
 */
public class TopicCostumer {
    public static void main(String[] args) throws JMSException, IOException {
        //1.获取两节工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.142:61616");
        //2.获取连接
        Connection connection = connectionFactory.createConnection();
        //3.启动连接
        connection.start();
        //4.会话  参数1:是否是事务提交  参数2::是否自动提交
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建
        Destination destination = session.createTopic("test_topic");
        //6.创建消费者对象
        MessageConsumer consumer = session.createConsumer(destination);
        //7.监听消息
        consumer.setMessageListener(message -> {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("接收到消息：" + textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
        //8.等待键盘输入
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
}
