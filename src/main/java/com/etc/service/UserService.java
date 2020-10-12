package com.etc.service;

import com.etc.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {

    //登录查询
    public User loginquery(String uname,String password);

    //分页查询方法 pageNum表示当前页 pageSize表示每页显示的记录条数
    public PageInfo<User> findPage(Integer pageNum, Integer pageSize);

    public List<User> find();

    public void userinsert(User u);

    public User get(int uid);

    public void mod(User u);

    public void del(int uid);
}
