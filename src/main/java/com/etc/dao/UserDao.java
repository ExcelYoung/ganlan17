package com.etc.dao;

import com.etc.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
   /* public List<User> find();

    public void userinsert(User u);

    public User get(int uid);

    public void mod(User u);

    public void del(int uid);*/

   //使用注解进行开发

    //拦截器新加的方法
    @Select("select * from Users where uname=#{uname} and password=#{password}")
    public User loginQuery(@Param("uname") String uname,@Param("password") String password);


    @Select("select * from Users order by uid desc")
    public List<User> find();
    @Insert("insert into Users values(default,#{uname},#{password})")
    @Options(useGeneratedKeys=true,keyProperty = "uid")
    public void userinsert(User u);
    @Select("select * from Users where uid=#{uid}")
    public User get(int uid);
    @Update("update Users set uname=#{uname},password=#{password} where uid=#{uid}")
    public void mod(User u);
    @Delete("delete from Users where uid=#{uid}")
    public void del(int uid);

}