package com.space.pojo;

public class AddFriMsg {
    private Integer msg_from;
    private Integer msg_to;


    public Integer getMsg_from() {
        return msg_from;
    }

    public void setMsg_from(Integer msg_from) {
        this.msg_from = msg_from;
    }

    public Integer getMsg_to() {
        return msg_to;
    }

    public void setMsg_to(Integer msg_to) {
        this.msg_to = msg_to;
    }

    @Override
    public String toString() {
        return "AddFriMsg{" +
                "msg_from=" + msg_from +
                ", to=" + msg_to +
                '}';
    }
}
