package com.space.pojo;

import java.util.List;

public class HomePageInfo {
    private Integer photoSum;
    private Integer diarySum;
    private Integer trendsSum;
    private Info info;
    private User user;
    private List<Diary> diaryList;

    public Integer getPhotoSum() {
        return photoSum;
    }

    public void setPhotoSum(Integer photoSum) {
        this.photoSum = photoSum;
    }

    public Integer getDiarySum() {
        return diarySum;
    }

    public void setDiarySum(Integer diarySum) {
        this.diarySum = diarySum;
    }

    public Integer getTrendsSum() {
        return trendsSum;
    }

    public void setTrendsSum(Integer trendsSum) {
        this.trendsSum = trendsSum;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Diary> getDiaryList() {
        return diaryList;
    }

    public void setDiaryList(List<Diary> diaryList) {
        this.diaryList = diaryList;
    }

    @Override
    public String toString() {
        return "HomePageInfo{" +
                "photoSum=" + photoSum +
                ", diarySum=" + diarySum +
                ", trendsSum=" + trendsSum +
                ", info=" + info +
                ", user=" + user +
                ", diaryList=" + diaryList +
                '}';
    }
}
