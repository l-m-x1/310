package com.space.pojo;


public class Mood {

  private Integer id;
  private Integer uid;
  private Integer type;
  private String content;

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


  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return "Mood{" +
            "id=" + id +
            ", uid=" + uid +
            ", type=" + type +
            ", content='" + content + '\'' +
            '}';
  }
}
