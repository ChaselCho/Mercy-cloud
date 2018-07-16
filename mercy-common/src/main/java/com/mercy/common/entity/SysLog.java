package com.mercy.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String type;

    private String title;

    private String createBy;

    private Date createTime;

    private Date updateTime;

    private String remoteAddr;

    private String userAgent;

    private String requestUri;

    private String method;

    private String params;

    private Long time;

    private String delFlag;

    private String exception;

    private String serviceId;
}
