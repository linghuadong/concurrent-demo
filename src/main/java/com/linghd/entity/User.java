package com.linghd.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ling on 2016/12/27.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 2798728906816377884L;
    private long id;
    private String username;
    private String password;
    private Date createdate;
    private Date lastDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

}
