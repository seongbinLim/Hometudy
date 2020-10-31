package com.hometudy.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class UserInfoRequest {
    @ApiModelProperty(required = true)
    @NotNull
    String uid;

    public String getUid() {
        return uid;
    } 
}
