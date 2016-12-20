/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author davidhl2
 */
@Entity
@Table(name = "D_Messages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DMessages.findAll", query = "SELECT d FROM DMessages d"),
    @NamedQuery(name = "DMessages.findById", query = "SELECT d FROM DMessages d WHERE d.id = :id"),
    @NamedQuery(name = "DMessages.findByContent", query = "SELECT d FROM DMessages d WHERE d.content = :content"),
    @NamedQuery(name = "DMessages.findByUserSender", query = "SELECT d FROM DMessages d WHERE d.userSender = :userSender"),
    @NamedQuery(name = "DMessages.findByDate", query = "SELECT d FROM DMessages d WHERE d.date = :date")})
public class DMessages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "user_sender")
    private String userSender;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
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
