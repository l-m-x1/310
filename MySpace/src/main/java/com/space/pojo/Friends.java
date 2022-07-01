package com.space.pojo;


public class Friends {

  private Integer id;
  private Integer fid;
  private Integer access;




  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getFid() {
    return fid;
  }

  public void setFid(Integer fid) {
    this.fid = fid;
  }


  public Integer getAccess() {
    return access;
  }

  public void setAccess(Integer access) {
    this.access = access;
  }

  @Override
  public String toString() {
    return "Friends{" +
            "id=" + id +
            ", fid=" + fid +
            ", access=" + access +
            '}';
  }
}
