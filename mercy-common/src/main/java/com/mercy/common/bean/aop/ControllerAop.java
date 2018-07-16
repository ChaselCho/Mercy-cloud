package com.mercy.common.bean.aop;

import cn.hutool.core.util.StrUtil;
import com.mercy.common.constant.SecurityConstants;
import com.mercy.common.util.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class ControllerAop {

    @Pointcut("execution(public com.mercy.common.util.ResultBean *(..))")
    public void pointCutResultBean(){}

    @Pointcut("execution(public com.baomidou.mybatisplus.plugins.Page *(..))")
    public void pointCutPage() {}
    @Around("pointCutResultBean()")
    public Object methodResultBeanHandler(ProceedingJoinPoint pjp) throws Throwable{
        return methodHandler(pjp);
    }
    @Around("pointCutPage()")
    public Object methodPageHandler(ProceedingJoinPoint pjp)throws Throwable{
        return methodHandler(pjp);
    }

    private Object methodHandler(ProceedingJoinPoint pjp)throws Throwable{
        long startTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String username = request.getHeader(SecurityConstants.USER_HEADER);
        if(StrUtil.isNotBlank(username)){
            log.info("Controller AOP get username:{}", username);
            UserUtils.setUser(username);
        }
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(pjp.getArgs()));
        Object result = pjp.proceed();
        log.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
        if (StrUtil.isNotEmpty(username)) {
            UserUtils.clearAllUserInfo();
        }
        return result;

    }

}
