package com.space.pojo;


public class Style {

  private Integer id;

  private Integer uid;
  private String type;
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


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Style{" +
            "id=" + id +
            ", uid=" + uid +
            ", type=" + type +
            '}';
  }
}
