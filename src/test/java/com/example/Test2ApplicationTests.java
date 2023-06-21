package com.example;

import com.example.common.util.UUIDUtil;
import com.example.dao.ProjectEntityMapper;
import com.example.dao.UserEntityMapper;
import com.example.dao.entity.ProjectEntity;
import com.example.dao.entity.UserEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.List;
//@SpringBootTest
public class Test2ApplicationTests {
    Logger log = Logger.getLogger(Test2ApplicationTests.class);
    @Test
    public void insert() throws Exception {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用projectMapper的方法
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(UUIDUtil.getOneUUID());
        projectEntity.setUserId("1");
        projectEntity.setProjectName("LS");
        int i = projectEntityMapper.insert(projectEntity);
        System.out.println("====================================================");
        if(i==0){
            // 记录error级别的信息
            log.info(">>insert项目插入测试失败");
        }else{
            log.info(">>insert项目插入测试成功");
        }

    }
    @Test
    public void modifyProjectById() throws Exception {
        System.out.println("====================================================");
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用projectMapper的方法
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("283dcf241cf245aea824dc10bbb3d682");
        projectEntity.setCreatedBy("admin");
        projectEntity.setProjectName("Project 8");
        projectEntity.setProjectContent("Project 8 description");
        projectEntity.setLastUpdatedBy("admin");
        //modifyProject
        int i = projectEntityMapper.updateByPrimaryKeySelective(projectEntity);
        if(i==0){
            // 记录error级别的信息

            log.info(">>modifyProjectById项目修改测试失败");
        }else{
            log.info(">>modifyProjectById项目修改测试成功");
        }

    }
    @Test
    public void queryProjectList() throws Exception {
        System.out.println("====================================================");
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用projectMapper的方法
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("");
        List<ProjectEntity> list = projectEntityMapper.queryProjectList(projectEntity);
        System.out.println("测试查询所有的项目列表");
        if (CollectionUtils.isEmpty(list)) {
            // 记录error级别的信息
            log.info(">>queryProjectList项目列表查询测试失败");
        } else {
            for (ProjectEntity entity : list) {
                System.out.println(entity);
            }
            // 记录info级别的信息
            log.info(">>queryProjectList用户列表查询测试成功");
        }
        System.out.println("====================================================");
        System.out.println("测试按名称搜索功能");
        System.out.println("有一个项目名字叫'第二个项目'，测试是否能查询到");
        projectEntity.setProjectName("第二个项目");
        projectEntity.setId("23");
        list = projectEntityMapper.queryProjectList(projectEntity);
        if (CollectionUtils.isEmpty(list)) {
            // 记录error级别的信息
            log.info(">queryProjectList不可以搜索到存在的项目，按名称搜索功能失败");
        } else {
            System.out.println(list);
            // 记录info级别的信息
            log.info(">queryProjectList可以搜索到存在的项目，按名称搜索功能成功");
        }
    }
    @Test
    public void deleteProjectById() throws Exception {
        System.out.println("====================================================");
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用projectMapper的方法
        //构造一个id为 9  的projectEntity对象

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("283dcf241cf245aea824dc10bbb3d685");
        System.out.println("deleteUserById方法：测试存在的项目是否能被删除");
        int i = projectEntityMapper.deleteProjectById(projectEntity);
        if(i==0){
            // 记录error级别的信息
            log.info(">>deleteProjectById：存在的项目不能被删除，deleteUserById方法测试失败");
        }else{
            // 记录info级别的信息
            log.info(">>deleteProjectById：存在的项目能被删除，deleteUserById方法测试成功");
        }
        System.out.println("---------------------------------------------");
        System.out.println("deleteUserById方法：测试不存在的项目是否能被删除");
        projectEntity.setId("100");
        i = projectEntityMapper.deleteProjectById(projectEntity);
        if(i==0){
            // 记录error级别的信息
            log.info(">>deleteProjectById：不存在的项目不能被删除，deleteUserById方法测试成功");
        }else{
            // 记录info级别的信息
            log.info(">>deleteProjectById：不存在的项目能被删除，deleteUserById方法测试失败");
        }
        System.out.println("====================================================");

    }

}
