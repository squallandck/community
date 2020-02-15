package com.xiaowei.community.community.mapper;

import com.xiaowei.community.community.model.Question;
import com.xiaowei.community.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);
}