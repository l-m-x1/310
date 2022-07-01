package servlet;

import com.space.pojo.Diary;
import com.space.pojo.Info;

public class Allinfo {
    private Info info;
    private Diary diary;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }

    @Override
    public String toString() {
        return "Allinfo{" +
                "info=" + info +
                ", diary=" + diary +
                '}';
    }

}
