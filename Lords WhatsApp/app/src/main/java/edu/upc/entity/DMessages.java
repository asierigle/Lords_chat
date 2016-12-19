package edu.upc.entity;

import java.io.Serializable;
import java.util.Date;


public class DMessages implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String content;
    private String userSender;
    private Date date;

    public DMessages() {
    }

    public DMessages(Integer id) {
        this.id = id;
    }

    public DMessages(Integer id, String content, String userSender, Date date) {
        this.id = id;
        this.content = content;
        this.userSender = userSender;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserSender() {
        return userSender;
    }

    public void setUserSender(String userSender) {
        this.userSender = userSender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DMessages)) {
            return false;
        }
        DMessages other = (DMessages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "practica2.DMessages[ id=" + id + " ]";
    }

}
