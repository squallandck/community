package com.xiaowei.community.community.dto;

import com.xiaowei.community.community.model.User;
import lombok.Data;

/**
 * Created by yxw on 2020/2/12
 */
@Data
public class QuestionDto {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModify;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private User user;
}
