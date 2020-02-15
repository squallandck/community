package com.xiaowei.community.community.service;

import com.xiaowei.community.community.mapper.UserMapper;
import com.xiaowei.community.community.model.User;
import com.xiaowei.community.community.model.UserExample;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yxw on 2020/2/13
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public void createOrUpdate(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(example);
        if (users == null || users.size() == 0) {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModify(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            user.setGmtModify(System.currentTimeMillis());
            userMapper.updateByPrimaryKeySelective(user);
        }
    }
}
