package servlet;

import com.alibaba.fastjson.JSON;
import com.space.pojo.Diary;
import com.space.pojo.Info;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServletTest {
    @Test
    public void testJSON(){
        Diary diary=new Diary();
        diary.setId(7);
        diary.setUid(4);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = format.format(date);
        diary.setTime(s);
        diary.setContent("今天天气很好");

        Info info = new Info();
        info.setId(2);
        info.setGender("男");
        info.setCity("武汉");
        Date date1 = new Date(2005-1900,1-1,1);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String s1 = format1.format(date);
        info.setBirthday(s1);

        Allinfo allinfo = new Allinfo();
        allinfo.setDiary(diary);
        allinfo.setInfo(info);
        String out = JSON.toJSONString(allinfo);
        System.out.println(out);
    }

    @Test
    public void testStream() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("D:\\project\\java\\310\\MySpace\\pom.xml");
        String toString = IOUtils.toString(fileInputStream);
        System.out.println(toString);
    }

}
