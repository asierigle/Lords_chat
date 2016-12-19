package edu.upc.util;

import java.util.Date;

public class Message {
    public Message(String _data, MessageTypes _type, String time, Date _date){content = _data; type = _type; realDate = _date;}

    public MessageTypes type;
    public String content;
    public Date realDate;
}