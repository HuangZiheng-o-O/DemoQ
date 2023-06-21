package com.example;

import com.example.beans.HttpResponseEntity;
import com.example.common.util.UUIDUtil;
import com.example.controller.UserController;
import com.example.dao.UserEntityMapper;
import com.example.dao.entity.UserEntity;
import jakarta.annotation.Resource;
import jdk.swing.interop.SwingInterOpUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import org.junit.jupiter.api.Test;


import org.junit.runner.RunWith;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.List;
import org.apache.log4j.Logger;
//@RunWith(SpringRunner.class)
//@SpringBootTest
class Test1ApplicationTests {

    Logger log = Logger.getLogger(Test1ApplicationTests.class);

    @Test
    public void insert() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        userEntity.setId("333");
        userEntity.setStatus("1");
        userEntity.setUsername("LS");
        userEntity.setPassword("123");
        int i = userEntityMapper.insert(userEntity);
        if(i==0){
            System.out.println("====================================================");
            log.info(">>insert用户插入测试失败");

        }else{
            // 记录info级别的信息
            System.out.println("====================================================");
            log.info(">>insert用户插入测试成功");

        }
    }

    @Test
    public void selectUserInfo() throws Exception {
        System.out.println("====================================================");
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Hzh");
        userEntity.setPassword("111");
        List<UserEntity> list = userEntityMapper.selectUserInfo(userEntity);

        System.out.println("selectUserInfo方法：测试不存在或者密码错误的用户是否能登陆");
        System.out.println("错误用户名字是：Hzh;错误密码是：111");
        if(CollectionUtils.isEmpty(list)){
            log.info(">>selectUserInfo测试：错误用户不能登陆，selectUserInfo方法测试成功");
        }else{
            System.out.println(list);
            // 记录info级别的信息
            log.info(">>selectUserInfo测试：错误用户可以登陆，selectUserInfo方法测试失败");
        }
        System.out.println("---------------------------------------------");
        userEntity.setUsername("Hzh");
        userEntity.setPassword("222");
        List<UserEntity> list2 = userEntityMapper.selectUserInfo(userEntity);
        System.out.println("selectUserInfo方法：测试存在且密码正确的用户是否能登陆");
        System.out.println("正确用户名字是：Hzh;正确密码是：222");
        if(CollectionUtils.isEmpty(list2)){
            log.info(">>selectUserInfo测试：正确用户不能登陆，selectUserInfo方法测试失败");
        }else{
            System.out.println(list2);
            // 记录info级别的信息
            log.info(">>selectUserInfo测试：正确用户可以登陆，selectUserInfo方法测试成功");
        }

    }

    @Test
    public void queryUserList() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        List<UserEntity> list = userEntityMapper.queryUserList(userEntity);
        System.out.println("====================================================");
        if(CollectionUtils.isEmpty(list)){
            System.out.println("queryUserList用户列表查询测试失败");
        }else{
            for (UserEntity userEntity1 : list) {
                System.out.println(userEntity1);
            }
            // 记录info级别的信息
            log.info(">>queryUserList用户列表查询测试成功");

        }
    }

    @Test
    public void deleteUserById() throws Exception {
        System.out.println("====================================================");
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        System.out.println("deleteUserById方法：测试不存在的用户是否能被删除");
        userEntity.setUsername("aaaaa");
        int i = userEntityMapper.deleteUserById(userEntity);
        if(i==0){
            // 记录error级别的信息
            log.info(">>deleteUserById测试：不存在的用户不能被删除，deleteUserById方法测试成功");
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>deleteUserById测试：不存在的用户被删除，deleteUserById方法测试失败");
        }
        System.out.println("---------------------------------------------");
        System.out.println("deleteUserById方法：测试存在的用户是否能被删除");
        System.out.println("查询数据库，有一个id为f4567e65a8af44739aa8e75ddcbfadd7的用户");
        System.out.println("删除他");
        userEntity.setId("f4567e65a8af44739aa8e75ddcbfadd7");
        int j = userEntityMapper.deleteUserById(userEntity);
        if(j==0){
            // 记录error级别的信息
            log.info(">>deleteUserById测试：存在的用户不能被删除，deleteUserById方法测试失败");
        }else{
            // 记录info级别的信息
            log.info(">>deleteUserById测试：存在的用户被删除，deleteUserById方法测试成功");
        }
        System.out.println("====================================================");
    }

}

