
package com.mercy.uac.common.listener;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mercy.uac.service.SysZuulRouteService;
import com.mercy.common.constant.CommonConstant;
import com.mercy.common.entity.SysZuulRoute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lengleng
 * @date 2018/5/16
 */
@Slf4j
@Component
public class RouteConfigInitListener implements CommandLineRunner {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SysZuulRouteService sysZuulRouteService;

    /**
     * Callback used to run the bean.
     * 初始化路由配置的数据，避免gateway 依赖业务模块
     *
     */
//    @EventListener(value = {EmbeddedServletContainerInitializedEvent.class})
//    public void init() {
//        log.info("开始初始化路由配置数据");
//        EntityWrapper wrapper = new EntityWrapper();
//        wrapper.eq(CommonConstant.DEL_FLAG, CommonConstant.STATUS_NORMAL);
//        List<SysZuulRoute> routeList = sysZuulRouteService.selectList(wrapper);
//        if (CollUtil.isNotEmpty(routeList)) {
//            redisTemplate.opsForValue().set(CommonConstant.ROUTE_KEY, routeList);
//            log.info("更新Redis中路由配置数据：{}条", routeList.size());
//        }
//        log.info("初始化路由配置数据完毕");
//    }

    @Override
    public void run(String... args) throws Exception {
        log.info("开始初始化路由配置数据");
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq(CommonConstant.DEL_FLAG, CommonConstant.STATUS_NORMAL);
        List<SysZuulRoute> routeList = sysZuulRouteService.selectList(wrapper);
        if (CollUtil.isNotEmpty(routeList)) {
            redisTemplate.opsForValue().set(CommonConstant.ROUTE_KEY, routeList);
            log.info("更新Redis中路由配置数据：{}条", routeList.size());
        }
        log.info("初始化路由配置数据完毕");
    }
}