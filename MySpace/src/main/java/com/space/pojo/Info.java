package com.space.pojo;


public class Info {

    private Integer id;
    private String gender;
    private String city;
    private java.sql.Date birthday;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public java.sql.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
