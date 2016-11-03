package edu.upc.util;

import java.util.Date;

public class Message {
    public String content;
    public int day;
    public String date;

    public Message()
    {
        
    }

    public Message(String a, int l, String d)
    {
        content = a;
        day = l;
        date = d;
    }
}
