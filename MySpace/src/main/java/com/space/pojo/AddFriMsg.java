package com.space.pojo;

public class AddFriMsg {
    private Integer id;
    private Integer from;
    private Integer to;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "AddFriMsg{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
