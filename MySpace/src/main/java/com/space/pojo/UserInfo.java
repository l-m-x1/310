package com.space.pojo;

public class UserInfo {
    private String avatar;
    private String username;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "HomePageInfo{" +
                "avatar='" + avatar + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
