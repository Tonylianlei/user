package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * 创建人:连磊
 * 日期: 2018/7/18. 15:30
 * 描述：
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.example.demo.dao"} , sqlSessionTemplateRef = "baseSqlSessionTemplate")
public class BaseConfig extends DataConnectPool implements TransactionManagementConfigurer {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.hikari.connection-timeout}")
    private int connectionTimeout;

    @Value("${spring.datasource.hikari.idle-timeout}")
    private int idleTimeout;

    @Value("${spring.datasource.hikari.max-lifetime}")
    private int maxLifetime;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int maximumPoolSize;

    /**
     * 配置dataSource，使用Hikari连接池
     */
    @Bean(name = "baseDataSource", destroyMethod = "close")
    @Primary
    public DataSource baseDataSource() {
        return buildDataSource(driverClassName,jdbcUrl,userName,password,connectionTimeout,idleTimeout,maxLifetime,maximumPoolSize);
    }

    @Bean(name = "baseSqlSessionFactory")
    @Primary
    public SqlSessionFactory baseSqlSessionFactory(@Qualifier("baseDataSource") DataSource cmsDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(cmsDataSource);
        Resource[] base = new PathMatchingResourcePatternResolver().getResources("classpath:com/example/demo/mapper/*.xml");
        Resource[] all = new Resource[base.length];
        int i =0;
        for(Resource a:base) {
            all[i++] = a;
        }
        bean.setMapperLocations(all);
        return bean.getObject();
    }

    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("baseDataSource") DataSource cmsDataSource) {
        return new DataSourceTransactionManager(cmsDataSource);
    }

    @Bean
    @Primary
    public SqlSessionTemplate baseSqlSessionTemplate(@Qualifier("baseSqlSessionFactory") SqlSessionFactory cmsSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(cmsSqlSessionFactory);
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return null;
    }
}
