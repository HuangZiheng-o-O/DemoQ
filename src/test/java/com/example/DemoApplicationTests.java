package com.example;


import com.example.beans.HttpResponseEntity;
import com.example.common.util.UUIDUtil;
import com.example.controller.ProjectController;
import com.example.controller.UserController;
import com.example.dao.ProjectEntityMapper;
import com.example.dao.UserEntityMapper;
import com.example.dao.UserEntityMapper;
import com.example.dao.entity.ProjectEntity;
import com.example.dao.entity.UserEntity;
import com.example.service.ProjectService;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {
    //    @Test
//    void contextLoads() {

    //    }
    Logger log = Logger.getLogger(DemoApplicationTests.class);
    @Resource
    private UserController userController;
    @Resource
    private UserService userService;
    @Resource
    private ProjectController projectController;
    @Resource
    private ProjectService projectService;

    @Test
    //查询失败
    public void testSelectUser(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("m");
        userEntity.setId("5");
        HttpResponseEntity httpResponseEntity = userController.queryUserList(userEntity);
        log.info("---结果---");
        //log.info(httpResponseEntity.getData().toString());
    }

    @Test
    //查询成功
    public void testSelectUser2(){
        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");
        HttpResponseEntity httpResponseEntity = userController.queryUserList(userEntity);
        log.info("---结果---");
        log.info(httpResponseEntity.getData().toString());
    }
    @Test
    //添加成功
    public void testAddUser(){
        UserEntity userEntity=new UserEntity();
        userEntity.setId("6");
        userEntity.setUsername("bbb");
        userEntity.setPassword("77");
        userEntity.setStatus("1");
        HttpResponseEntity httpResponseEntity=userController.addUserInfo(userEntity);
        log.info("---结果---");
        //log.info(httpResponseEntity.getData().toString());
    }

    @Test
    //修改失败
    public void testModifyUser(){
        UserEntity userEntity=new UserEntity();
        userEntity.setId("a2022f492e9443a6b4eb9744242425e7");
        userEntity.setUsername("676767");
        HttpResponseEntity httpResponseEntity=userController.modifyUserInfo(userEntity);
        log.info("---结果---");
        //log.info(httpResponseEntity.getData().toString());
    }

    @Test
    //修改成功
    public void testModifyUser1(){
        UserEntity userEntity=new UserEntity();
        userEntity.setId("111");
        userEntity.setUsername("111");
        userEntity.setPassword("123");
        HttpResponseEntity httpResponseEntity=userController.modifyUserInfo(userEntity);
        log.info("---结果---");
        log.info(httpResponseEntity.getData().toString());
    }


    @Test
    //删除失败
    public void testDeleteUser(){
        UserEntity userEntity=new UserEntity();
        userEntity.setId("8b9f95bf3f2a423790356d01059b40db");
        HttpResponseEntity httpResponseEntity=userController.deleteUser(userEntity);
        log.info("---结果---");
        //log.info(httpResponseEntity.getData().toString());
    }

    @Test
    //删除成功
    public void testDeleteUser1(){
        UserEntity userEntity=new UserEntity();
        userEntity.setId("222");
        HttpResponseEntity httpResponseEntity=userController.deleteUser(userEntity);
        log.info("---结果---");
        log.info(httpResponseEntity.getData().toString());
    }

    @Test
    //登录失败
    public void testLoginUser(){
        UserEntity userEntity=new UserEntity();
        userEntity.setUsername("admin");
        userEntity.setPassword("123456");
        HttpResponseEntity httpResponseEntity=userController.userLogin(userEntity);
        log.info("---结果---");
        //log.info(httpResponseEntity.getData().toString());
    }

    @Test
    //登录成功
    public void testLoginUser1(){
        UserEntity userEntity=new UserEntity();
        userEntity.setUsername("admin");
        userEntity.setPassword("123");
        HttpResponseEntity httpResponseEntity=userController.userLogin(userEntity);
        log.info("---结果---");
        log.info(httpResponseEntity.getData().toString());
    }

    @Test
    //添加成功
    public void testAddProject(){
        ProjectEntity projectEntity=new ProjectEntity();
        projectEntity.setProjectName("a");
        projectEntity.setProjectContent("yo");
        HttpResponseEntity httpResponseEntity=projectController.addProjectInfo(projectEntity);
        log.info("---结果---");
        log.info(httpResponseEntity.getData().toString());
    }


    @Test
    //删除成功
    public void testdeleProject(){
        ProjectEntity projectEntity=new ProjectEntity();
        projectEntity.setId("d653afe08cf24d31b9a0ff5d773a51b5");
        HttpResponseEntity httpResponseEntity=projectController.deleteProjectById(projectEntity);
        log.info("---结果---");
        log.info(httpResponseEntity.getData().toString());
    }
    @Test
    //删除失败
    public void testdeleProject1(){
        ProjectEntity projectEntity=new ProjectEntity();
        projectEntity.setId("8");
        HttpResponseEntity httpResponseEntity=projectController.deleteProjectById(projectEntity);
        log.info("---结果---");
    }

    @Test
    //修改成功
    public void testmodiyProject(){
        ProjectEntity projectEntity=new ProjectEntity();
        projectEntity.setId("36f11b87be23455594d80c43fc91836e");
        projectEntity.setProjectName("5555");
        HttpResponseEntity httpResponseEntity=projectController.modifyProjectInfo(projectEntity);
        log.info("---结果---");
        log.info(httpResponseEntity.getData().toString());
    }
    @Test
    //修改失败
    public void testmodiyProject1(){
        ProjectEntity projectEntity=new ProjectEntity();
        projectEntity.setId("9");
        projectEntity.setProjectName("aaaa");
        HttpResponseEntity httpResponseEntity=projectController.modifyProjectInfo(projectEntity);
        log.info("---结果---");
    }
    @Test
    //查询成功
    public void testQueryProject(){
        ProjectEntity projectEntity=new ProjectEntity();
        projectEntity.setProjectName("第二个项目");
        projectEntity.setId("4cd6ccb65c894eafaa70b12330f8c2f8");
        HttpResponseEntity httpResponseEntity=projectController.queryProjectList(projectEntity);
        log.info("---结果---");
        //log.info(httpResponseEntity.getData().toString());
    }
    @Test
    //查询失败
    public void testQueryProject1(){
        ProjectEntity projectEntity=new ProjectEntity();
        projectEntity.setProjectName("0000");
        HttpResponseEntity httpResponseEntity=projectController.queryProjectList(projectEntity);
        log.info("---结果---");

    }




    //@Test
    /*public void queryUserList() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        List<UserEntity> list = userEntityMapper.queryUserList(userEntity);
        if(CollectionUtils.isEmpty(list)){
            // 记录error级别的信息
        }else{
            System.out.println(list);
            // 记录info级别的信息
            log.info(">>queryUserList用户列表查询测试成功");
        }
    }

    @Test
    public void selectUserInfo() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("admin");
        userEntity.setPassword("123");
        List<UserEntity> list = userEntityMapper.selectUserInfo(userEntity);
        if(CollectionUtils.isEmpty(list)){
            // 记录error级别的信息
        }else{
            System.out.println(list);
            // 记录info级别的信息
            log.info(">>qselectUserInfo用户登录测试成功");
        }
    }

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
        userEntity.setId(UUIDUtil.getOneUUID());
        userEntity.setStatus("1");
        userEntity.setUsername("LS");
        userEntity.setPassword("123");
        int i = userEntityMapper.insert(userEntity);
        if(i==0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>insert用户插入测试成功");
        }
    }

    @Test
    public void deleteUserById() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("aaaaa");
        userEntity.setId("111");
        int i = userEntityMapper.deleteUserById(userEntity);
        if(i==0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>delete用户删除测试成功");
        }
    }
    @Test
    public void modifyUsertInfo() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        userEntity.setId("123");
        userEntity.setUsername("abc");
        userEntity.setPassword("123");
        userEntity.setStatus("1");
        int i = userEntityMapper.updateByprimaryKeySelective(userEntity);
        if(i==0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>delete项目修改测试成功");
        }
    }

    @Test
    public void queryProjectList() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用userMapper的方法
        ProjectEntity projectEntity = new ProjectEntity();
        List<ProjectEntity> list = projectEntityMapper.queryProjectList(projectEntity);
        if(CollectionUtils.isEmpty(list)){
            // 记录error级别的信息
        }else{
            System.out.println(list);
            // 记录info级别的信息
            log.info(">>queryProjectList项目列表查询测试成功");
        }
    }

    @Test
    public void projectInsert() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用userMapper的方法
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(UUIDUtil.getOneUUID());
        projectEntity.setProjectName("dfasda");
        projectEntity.setProjectContent("fdfsfasdfa");
        int i = projectEntityMapper.insert(projectEntity);
        if(i==0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>insert项目插入测试成功");
        }
    }

    @Test
    public void deleteProjectById() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用userMapper的方法
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("fdasf");
        projectEntity.setId("111");
        int i = projectEntityMapper.deleteProjectById(projectEntity);
        if(i==0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>delete项目删除测试成功");
        }
    }

    @Test
    public void modifyProjectInfo() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用userMapper的方法
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("fdasf");
        projectEntity.setId("111");
        int i = projectEntityMapper.updateByprimaryKeySelective(projectEntity);
        if(i==0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>delete项目修改测试成功");
        }
    }*/

}
