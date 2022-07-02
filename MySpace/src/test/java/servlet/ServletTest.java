package servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.space.pojo.Diary;
import com.space.pojo.Friends;
import com.space.pojo.Info;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Test
    public void testJSONInt(){

        HashMap<String, Object> map = new HashMap<>();
        map.put("data",123);
        JSONObject jsonObject = new JSONObject(map);
//        String s = jsonObject.toJSONString();
        System.out.println(jsonObject.getInteger("data"));
    }

    @Test
    public void testJSONAdd(){
        JSONObject jsonObject1 = new JSONObject();
//        JSONObject jsonObject2=new JSONObject();
//        jsonObject2.put("name","张三");
//        jsonObject2.put("password","134");
//        jsonObject1.put("jsob",jsonObject2);
//        jsonObject1.put("jsob",jsonObject2);
//        System.out.println(jsonObject1.toJSONString());
//        JSONArray jsonArray=new JSONArray();
        List<Friends> friends=new ArrayList<>();
        Friends friends1 = new Friends();
        friends1.setId(1);
        friends1.setFid(2);
        friends.add(friends1);
        Friends friends2 = new Friends();
        friends2.setId(1);
        friends2.setFid(2);
        friends.add(friends2);
        System.out.println(friends.toString());
        System.out.println(JSONObject.toJSONString(friends));
        jsonObject1.put("data",friends);
        System.out.println(jsonObject1.toJSONString());
    }

    @Test
    public void testUUID(){
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        String path="hello"+ uuid +".jpg";
        System.out.println(path);
    }

    @Test
    public void testJSONARRAY(){
        int[]a=new int[]{1,2,3,4,5,6,7,8,9,0};

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("array",a);
        JSONArray array = jsonObject1.getJSONArray("array");
        for (Object o:array){
            System.out.println(o);
        }
        System.out.println(jsonObject1);

    }

    @Test
    public void arrayList(){
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        System.out.println(JSON.toJSONString(integers));
    }
}

