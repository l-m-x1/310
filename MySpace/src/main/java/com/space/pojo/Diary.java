package com.space.pojo;


public class Diary {

  private Integer id;
  private Integer uid;

  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }

  private java.sql.Timestamp time;
  private String content;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public java.sql.Timestamp getTime() {
    return time;
  }

  public void setTime(java.sql.Timestamp time) {
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
