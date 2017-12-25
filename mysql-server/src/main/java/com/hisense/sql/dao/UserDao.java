package com.hisense.sql.dao;

import com.github.pagehelper.Page;
import com.hisense.sql.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper//这里的Mapper是Mybatis自己定义的注解。
public interface UserDao {

    //根据age查找info
    List<User> get(int age);

    //插入数据
    @Insert("insert into user(userId,userCode,age,mail,phone,groupId," +
            "createTime,createBy,modifiedTime,modifiedBy)" +
            "values (#{userId,jdbcType=VARCHAR}, #{userCode,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER}," +
            "#{mail,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, #{groupId,jdbcType=INTEGER}," +
            "#{createTime,jdbcType=TIMESTAMP}, #{createBy,jdbcType=VARCHAR}, #{modifiedTime,jdbcType=TIMESTAMP}," +
            "#{modifiedBy,jdbcType=VARCHAR})")

    void insertUser(User user);
    //更新数据
    @Update("update user set userId=#{userId,jdbcType=VARCHAR}," +
            "            userCode=#{userCode,jdbcType=VARCHAR}," +
            "            age=#{age,jdbcType=INTEGER}," +
            "            mail=#{mail,jdbcType=VARCHAR}," +
            "            phone=#{phone,jdbcType=VARCHAR}," +
            "            groupId=#{groupId,jdbcType=INTEGER}," +
            "            createTime=#{createTime,jdbcType=TIMESTAMP}," +
            "            createBy=#{createBy,jdbcType=VARCHAR}," +
            "            modifiedTime=#{modifiedTime,jdbcType=TIMESTAMP}," +
            "            modifiedBy=#{modifiedBy,jdbcType=VARCHAR} where userId=#{userId,jdbcType=VARCHAR}")
    void updateUser(User  user);

    //根据userId删除一个用户的数据
    @Delete("delete from user where useId = #{useId,jdbcType=VARCHAR}")
    void deletdUserById(String userId);
    //根据userCode删除一个用户的数据
    @Delete("delete from user where userCode = #{userCode,jdbcType=VARCHAR}")
    void deletdUserByCode(String userCode);
    //根据userCode取出一个用户的数据

    @Select("select * from user WHERE userCode = #{userCode,jdbcType=VARCHAR}")
    User getUserByCode(String userCode);
    //根据userId取出一个用户的数据
    @Select("select * from user WHERE userId = #{userId,jdbcType=VARCHAR}")
    User getUserById(String userId);
    @Select("select userCode,age,phone,modifiedTime from user ORDER BY modifiedTime DESC")
    Page<User> getUserList();

    @Select("select * from user ORDER BY modifiedTime DESC")
    Page<User> getInfos(String parameter);
    @Select("select * from user WHERE userCode = #{parameter,jdbcType=VARCHAR} or userCode like CONCAT('%',#{parameter},'%')")
    Page<User> getInfosByuserCode(String parameter);
    @Select("select * from user WHERE age = #{parameter,jdbcType=INTEGER} ")
    Page<User> getInfosByage(Integer parameter);
    @Select("select * from user WHERE mail = #{parameter,jdbcType=VARCHAR} or mail like CONCAT('%',#{parameter},'%')")
    Page<User> getInfosBymail(String parameter);
    @Select("select * from user WHERE phone = #{parameter,jdbcType=VARCHAR}")
    Page<User> getInfosByphone(String parameter);
}