
package com.mercy.uac.common.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.mercy.common.bean.interceptor.DataScopeInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lengleng
 * @date 2017/10/29
 */
@Configuration
@MapperScan("com.mercy.uac.mapper")
public class MybatisPlusConfig {
    /**
     * 分页插件
     *
     * @return PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 数据权限插件
     *
     * @return DataScopeInterceptor
     */
    @Bean
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor();
    }
}