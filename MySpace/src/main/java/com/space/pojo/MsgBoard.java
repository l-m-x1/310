package com.space.pojo;


public class MsgBoard {

  private long id;
  private long wrid;
  private String content;
  private java.sql.Timestamp time;
  private long floor;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getWrid() {
    return wrid;
  }

  public void setWrid(long wrid) {
    this.wrid = wrid;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public java.sql.Timestamp getTime() {
    return time;
  }

  public void setTime(java.sql.Timestamp time) {
    this.time = time;
  }


  public long getFloor() {
    return floor;
  }

  public void setFloor(long floor) {
    this.floor = floor;
  }

}
