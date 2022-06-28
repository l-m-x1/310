package com.space.pojo;


import java.sql.Date;

public class Diary {

    private Integer id;
    private Integer uid;


    private String time;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Diary{" +
                "id=" + id +
                ", uid=" + uid +
                ", time=" + time +
                ", content='" + content + '\'' +
                '}';
    }
}
