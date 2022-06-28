package com.space.pojo;


public class Photos {

  private Integer id;

  private Integer uid;

  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }

  private java.sql.Timestamp time;
  private String path;


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


  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  @Override
  public String toString() {
    return "Photos{" +
            "id=" + id +
            ", uid=" + uid +
            ", time=" + time +
            ", path='" + path + '\'' +
            '}';
  }
}

