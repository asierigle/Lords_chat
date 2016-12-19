package edu.upc.util;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    public MessageTypes type;
    public String content;
    public Date realDate;
}