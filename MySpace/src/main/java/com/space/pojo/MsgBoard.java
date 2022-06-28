package com.space.pojo;


public class MsgBoard {

  private Integer id;
  private Integer wrid;
  private String content;
  private java.sql.Timestamp time;
  private Integer floor;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getWrid() {
    return wrid;
  }

  public void setWrid(Integer wrid) {
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


  public Integer getFloor() {
    return floor;
  }

  public void setFloor(Integer floor) {
    this.floor = floor;
  }

}
