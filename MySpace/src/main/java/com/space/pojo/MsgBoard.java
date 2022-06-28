package com.space.pojo;


public class MsgBoard {

  private Integer id;
  private Integer uid;
  private Integer wrid;
  private String content;
  private String time;
  private Integer floor;


  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }

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


  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }


  public Integer getFloor() {
    return floor;
  }

  public void setFloor(Integer floor) {
    this.floor = floor;
  }

  @Override
  public String toString() {
    return "MsgBoard{" +
            "id=" + id +
            ", uid=" + uid +
            ", wrid=" + wrid +
            ", content='" + content + '\'' +
            ", time=" + time +
            ", floor=" + floor +
            '}';
  }
}
