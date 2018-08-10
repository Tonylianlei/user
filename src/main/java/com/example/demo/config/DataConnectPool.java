package com.example.demo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 创建人:连磊
 * 日期: 2018/8/10. 15:48
 * 描述：
 */
public class DataConnectPool {

    public HikariDataSource buildDataSource(String driverClassName, String jdbcUrl, String userName, String password, int connectionTimeout, int idleTimeout, int maxLifetime, int maximumPoolSize){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(userName);
        config.setPassword(password);
        // 等待从连接池中获得连接的最大时长（毫秒），
        // 超过这个时长还没可用的连接则发生SQLException， 缺省:30秒
        config.setConnectionTimeout(connectionTimeout);
        // 一个连接idle状态的最大时长（毫秒），超时则被释放（retired），缺省:10分钟
        config.setIdleTimeout(idleTimeout);
        // 一个连接的生命时长（毫秒），超时而且没被使用则被释放（retired），缺省:30分钟
        // 建议设置比数据库超时时长少30秒以上
        config.setMaxLifetime(maxLifetime);
        // 连接池中允许的最大连接数。缺省值：10；推荐的公式：((core_count * 2) + effective_spindle_count)
        config.setMaximumPoolSize(maximumPoolSize);
        return new HikariDataSource(config);
    }

}
