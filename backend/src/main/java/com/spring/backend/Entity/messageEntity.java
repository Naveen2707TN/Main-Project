package com.spring.backend.Entity;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "message")
public class messageEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String msg;

    @Column(nullable = false)
    private Long sedid;

    @Column(nullable = false)
    private Long recid;

    @Column(nullable = false)
    private Date senddate;

    public messageEntity() {
    }

    public messageEntity(Long id, String msg, Long sedid, Long recid, Date senddate) {
        this.id = id;
        this.msg = msg;
        this.sedid = sedid;
        this.recid = recid;
        this.senddate = senddate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getSedid() {
        return sedid;
    }

    public void setSedid(Long sedid) {
        this.sedid = sedid;
    }

    public Long getRecid() {
        return recid;
    }

    public void setRecid(Long recid) {
        this.recid = recid;
    }

    public Date getSenddate() {
        return senddate;
    }

    public void setSenddate(Date senddate) {
        this.senddate = senddate;
    }

    
}
