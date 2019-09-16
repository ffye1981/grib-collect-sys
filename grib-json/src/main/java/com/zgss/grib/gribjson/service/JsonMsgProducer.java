package com.zgss.grib.gribjson.service;

import com.zgss.grib.entity.JsonQueueFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class JsonMsgProducer {
    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${activemq.contourQueueName}")
    private String contourQueueName;
    /**
     * 发送消息，message是待发送的消息
     * @param message
     */
    public boolean sendMessage(final String message) {
        boolean flag = false;
        try {
            jmsTemplate.convertAndSend(message);
            if(this.contourQueueName!=null) {
                jmsTemplate.convertAndSend(contourQueueName,message);
            }
            flag = true;
        }catch (Exception e){
        }
        return  false;
    }

    /**
     * 发送消息，estination是发送到的队列，message是待发送的消息
     * @param destination
     * @param message
     * @return
     */
    public boolean send(String destination, final String message){
        boolean flag = false;
        try {
            jmsTemplate.convertAndSend(destination, message);
            if(this.contourQueueName!=null) {
                jmsTemplate.convertAndSend(contourQueueName,message);
            }
            flag = true;
        }catch (Exception e){
        }
        return  false;
    }

    /**
     * 发送消息，message是待发送的消息
     * @param jsonFile
     */
    public boolean sendMessage(JsonQueueFile jsonFile) {
        boolean flag = false;
        try {
            jmsTemplate.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createObjectMessage(jsonFile);
                }
            });
            if(this.contourQueueName!=null) {
                jmsTemplate.send(contourQueueName,new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createObjectMessage(jsonFile);
                    }
                });
            }
            flag = true;
        }catch (Exception e){
        }
        return  false;
    }

    /**
     * 发送消息，message是待发送的消息
     * @param jsonFile
     */
    public boolean sendMessage(String destination, JsonQueueFile jsonFile) {
        boolean flag = false;
        try {
            jmsTemplate.send(destination,new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createObjectMessage(jsonFile);
                }
            });
            if(this.contourQueueName!=null) {
                jmsTemplate.send(contourQueueName,new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createObjectMessage(jsonFile);
                    }
                });
            }
            flag = true;
        }catch (Exception e){
        }
        return  false;
    }


    public String getContourQueueName() {
        return contourQueueName;
    }

    public void setContourQueueName(String contourQueueName) {
        this.contourQueueName = contourQueueName;
    }
}
