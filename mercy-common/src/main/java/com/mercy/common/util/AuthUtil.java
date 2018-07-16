package com.mercy.common.util;

import java.io.IOException;

import com.mercy.common.constant.CommonConstant;
import com.mercy.common.constant.enums.ErrorCodeEnum;
import com.mercy.common.util.exception.BusinessException;
import com.mercy.common.util.exception.CheckedException;
import org.springframework.security.crypto.codec.Base64;

import javax.servlet.http.HttpServletRequest;

/**
/*
 * @Author fcao sunf1ower@126.com
 * @Description //TODO 
 * @Date 11:40 2018/7/10
 *
 * */
public class AuthUtil {
    private static final String BASIC_ = "Basic ";


    public static String[] extractAndDecodeHeader(String header) throws IOException {
        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decode;
        try {
            decode = Base64.decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new CheckedException(ErrorCodeEnum.AUTH10001002.msg());

        }
        String token = new String(decode, CommonConstant.UTF8);
        int delim = token.indexOf(":");
        if(delim == -1){
            throw new CheckedException(ErrorCodeEnum.AUTH10001001.msg());
        }
        return new String[]{token.substring(0,delim),token.substring(delim+1)};
    }
    /**
     *Request from the header get clientId/clientsecect
     * @param request
     * @return Striung []
     * @throws IOException
     */
    public static String [] extractAndDecodeHeader(HttpServletRequest request) throws IOException {
        String header =request.getHeader(CommonConstant.REQ_HEADER);
        if(header == null || !header.startsWith(BASIC_)){
            throw new CheckedException(ErrorCodeEnum.AUTH10001003.msg());
        }
        return extractAndDecodeHeader(header);
    }

}
