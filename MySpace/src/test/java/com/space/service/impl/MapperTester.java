package com.space.service.impl;

import com.space.mapper.DiaryMapper;
import com.space.mapper.FriendsMapper;
import com.space.mapper.InfoMapper;
import com.space.mapper.MoodMapper;
import com.space.pojo.Diary;
import com.space.pojo.Friends;
import com.space.pojo.Info;
import com.space.pojo.Mood;
import com.space.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MapperTester {
    SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();
    @Test
    public void testDiary(){
        SqlSession session=factory.openSession();
        DiaryMapper mapper = session.getMapper(DiaryMapper.class);
        Diary diary=new Diary();
        diary.setId(7);
        diary.setUid(4);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = format.format(date);
        diary.setTime(s);
        diary.setContent("今天天气很好");
//        mapper.insert(diary);
//        mapper.delete(11);
//        mapper.update(diary);
//        System.out.println(mapper.selectByUid(3));
//        System.out.println(mapper.selectById(7));

        session.commit();
        session.close();
    }
    @Test
    public void testFriends(){
        SqlSession session = factory.openSession();
        FriendsMapper mapper = session.getMapper(FriendsMapper.class);
        Friends friends=new Friends();
        friends.setId(2);
        friends.setFid(3);
        friends.setAccess(0);
//        mapper.insert(friends);
//        mapper.delete(friends);
//        mapper.update(friends);

//        System.out.println(mapper.selectById(2));
        session.commit();
        session.close();
    }
    @Test
    public void testInfo(){
        SqlSession session = factory.openSession();
        InfoMapper mapper = session.getMapper(InfoMapper.class);
        Info info = new Info();
        info.setId(2);
        info.setGender("男");
        info.setCity("武汉");
        Date date = new Date(2005-1900,1-1,1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String s = format.format(date);
        info.setBirthday(s);
//        mapper.insert(info);
//        mapper.delete(3);
//        mapper.update(info);
//        System.out.println(mapper.selectById(1));
        session.commit();
        session.close();
    }
    @Test
    public void testMood(){
        SqlSession session = factory.openSession();
        MoodMapper mapper = session.getMapper(MoodMapper.class);
        Mood mood = new Mood();
        mood.setId(1);
        mood.setUid(5);
        mood.setType(0);
        mood.setContent("高兴");
//        mapper.insert(mood);
//        mapper.delete(1);
//        mapper.update(mood);
        ;
//        System.out.println(mapper.selectById(1));
        System.out.println(mapper.selectByUid(5));
        session.commit();
        session.close();
    }
    @Test
    public void t(){
        Calendar calendar=Calendar.getInstance();
        calendar.set(2000,12,1);
        Date date = calendar.getTime();
        System.out.println(date);
    }
}
