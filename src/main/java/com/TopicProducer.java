package com;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author ll
 * @Date 2018/11/26 17:00
 */
public class TopicProducer {
    public static void main(String[] args) throws JMSException {
        //1.获取两节工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.142:61616");
        //2.获取连接
        Connection connection = connectionFactory.createConnection();
        //3.启动连接
        connection.start();
        //4.会话  参数1:是否是事务提交  参数2::是否自动提交
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建队列对象
        Destination destination = session.createTopic("test_topic");
        //6.创建消息生产者
        javax.jms.MessageProducer producer = session.createProducer(destination);
        //7.创建消息
        Message message = session.createTextMessage("你好");
        //8.发送消息
        producer.send(message);

        //9.关闭
        producer.close();
        session.close();
        connection.close();
    }
}
