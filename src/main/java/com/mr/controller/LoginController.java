package com.mr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yaodd on 2018/10/17.
 */
@Controller
public class LoginController {

    /*@Autowired
    private JmsTemplate jmsTemplate;

    @Resource(name = "dx-destination")
    private Destination dxDestination;

    @Resource(name = "email-destination")
    private Destination emailDestination;*/

    @RequestMapping("regist")
    @ResponseBody
    public String regist(){

        //注册
        //注册成功之后
        System.out.println("注册成功");
        System.out.println("注册成功1");

        //放在消息队列当中
        //发送短信
        //MyActiveMqUtil.sendMq(jmsTemplate,dxDestination,"短信");
       /* jmsTemplate.send(dxDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("注册成功，发送邮件");
            }
        });*/

        //发送邮件
       // MyActiveMqUtil.sendMq(jmsTemplate,emailDestination,"邮件");
        /*jmsTemplate.send(emailDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("注册成功，发送短信");
            }
        });*/

        return "success";
    }

}
