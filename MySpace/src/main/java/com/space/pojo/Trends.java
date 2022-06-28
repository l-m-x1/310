package com.space.pojo;


public class Trends {

  private Integer id;
  private String content;
  private Integer likes;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public Integer getLikes() {
    return likes;
  }

  public void setLikes(Integer likes) {
    this.likes = likes;
  }

  @Override
  public String toString() {
    return "Trends{" +
            "id=" + id +
            ", content='" + content + '\'' +
            ", likes=" + likes +
            '}';
  }
}
