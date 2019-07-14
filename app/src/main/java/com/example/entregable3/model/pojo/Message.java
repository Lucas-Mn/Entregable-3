package com.example.entregable3.model.pojo;

import java.util.Date;

public class Message {

    private String sender_name;
    private String sender_uid;
    private String content;
    private Date date;


    public Message(String sender_name, String sender_uid, String content, Date date) {
        this.sender_name = sender_name;
        this.sender_uid = sender_uid;
        this.content = content;
        this.date = date;
    }

    public Message(){}

    public Date getDate() { return date; }
    public String getContent() { return content; }
    public String getSender_uid() { return sender_uid; }
    public String getSender_name() { return sender_name; }
}
