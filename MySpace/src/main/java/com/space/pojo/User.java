package com.space.pojo;


public class User {

  private Integer id;
  private Integer uid;

  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }

  private int account;
  private String username;
  private String password;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public int getAccount() {
    return account;
  }

  public void setAccount(int account) {
    this.account = account;
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
            ", uid=" + uid +
            ", account=" + account +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}
