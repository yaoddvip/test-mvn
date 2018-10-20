package com.mr.util;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by yaodd on 2018/10/17.
 */
public class MyActiveMqUtil {

    /**
     * 发送mq
     * @param jmsTemplate
     * @param destination
     * @param msg
     */
    public static void sendMq(JmsTemplate jmsTemplate, Destination destination, String msg){
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }
}
