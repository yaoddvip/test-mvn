package com.mr.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by yaodd on 2018/10/17.
 * mq的消费者
 */
public class Consumer implements MessageListener{

    @Override
    public void onMessage(Message msg) {
        try {
            TextMessage txtMsg = (TextMessage) msg;
            String message = txtMsg.getText();

            System.out.println(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
