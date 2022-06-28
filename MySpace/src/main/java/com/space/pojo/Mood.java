package com.space.pojo;


public class Mood {

  private Integer id;
  private Integer type;
  private String content;


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
            ", type=" + type +
            ", content='" + content + '\'' +
            '}';
  }
}
