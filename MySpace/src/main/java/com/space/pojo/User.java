package com.space.pojo;


public class User {

  private Integer id;
  private Long account;
  private String username;
  private String password;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Long getAccount() {
    return account;
  }

  public void setAccount(Long account) {
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
            ", account=" + account +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}
