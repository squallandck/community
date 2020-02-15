package com.xiaowei.community.community.dto;

import lombok.Data;

/**
 * Created by yxw on 2020/2/10
 */
@Data
public class AccessTokenDto {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
