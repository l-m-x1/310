package com.space.pojo;


public class User {

  private Integer id;
  private Long acount;
  private String username;
  private String password;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Long getAcount() {
    return acount;
  }

  public void setAcount(Long acount) {
    this.acount = acount;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", acount=" + acount +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}
