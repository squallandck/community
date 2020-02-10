package com.xiaowei.community.community.mapper;

import com.xiaowei.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by yxw on 2020/2/10
 */
@Mapper
public interface UserMapper {

    @Insert("Insert into user(name, account_id, token, gmt_create, gmt_modify) values (#{name}, #{accountId},#{token},#{gmtCreate},#{gmtModify})")
    void insert(User user);
}
