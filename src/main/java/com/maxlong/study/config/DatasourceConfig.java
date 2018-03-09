package com.maxlong.study.config;

import com.maxlong.study.datasource.DynamicDataSource;
import com.maxlong.study.enums.DatabaseType;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/4/24 18:13
 * 类说明
 */
@Configuration
@Order(1)
@MapperScan(basePackages = "com.maxlong.study.mapper")
public class DatasourceConfig {

    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties(prefix = "datasource.hikari.acq")
    public HikariConfig acqHikariConfig() {
        HikariConfig acqHikariConfig = new HikariConfig();
        acqHikariConfig.setDataSourceClassName(env.getProperty("datasource.hikari.com.dataSourceClassName"));
        acqHikariConfig.setAutoCommit(Boolean.valueOf(env.getProperty("datasource.hikari.com.isAutoCommit")));
        acqHikariConfig.setConnectionTestQuery(env.getProperty("datasource.hikari.com.connectionTestQuery"));
        acqHikariConfig.setMinimumIdle(Integer.valueOf(env.getProperty("datasource.hikari.com.minIdle")));
        acqHikariConfig.setMaximumPoolSize(Integer.valueOf(env.getProperty("datasource.hikari.com.maxPoolSize")));
        acqHikariConfig.setConnectionTimeout(Long.valueOf(env.getProperty("datasource.hikari.com.connectionTimeout")));
        acqHikariConfig.setIdleTimeout(Long.valueOf(env.getProperty("datasource.hikari.com.idleTimeout")));
        acqHikariConfig.setMaxLifetime(Long.valueOf(env.getProperty("datasource.hikari.com.maxLifetime")));
        return acqHikariConfig;
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.hikari.pay")
    public HikariConfig payHikariConfig() {
        HikariConfig payHikariConfig = new HikariConfig();
        payHikariConfig.setDataSourceClassName(env.getProperty("datasource.hikari.com.dataSourceClassName"));
        payHikariConfig.setAutoCommit(Boolean.valueOf(env.getProperty("datasource.hikari.com.isAutoCommit")));
        payHikariConfig.setConnectionTestQuery(env.getProperty("datasource.hikari.com.connectionTestQuery"));
        payHikariConfig.setMinimumIdle(Integer.valueOf(env.getProperty("datasource.hikari.com.minIdle")));
        payHikariConfig.setMaximumPoolSize(Integer.valueOf(env.getProperty("datasource.hikari.com.maxPoolSize")));
        payHikariConfig.setConnectionTimeout(Long.valueOf(env.getProperty("datasource.hikari.com.connectionTimeout")));
        payHikariConfig.setIdleTimeout(Long.valueOf(env.getProperty("datasource.hikari.com.idleTimeout")));
        payHikariConfig.setMaxLifetime(Long.valueOf(env.getProperty("datasource.hikari.com.maxLifetime")));
        return payHikariConfig;
    }


    /**
     * @return
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     */
    @Bean
    public DataSource acqDataSource() {
        HikariConfig acqHikariConfig = payHikariConfig();
        HikariDataSource source = new HikariDataSource(acqHikariConfig());
        return source;
    }

    @Bean
    public DataSource payDataSource() {
        HikariConfig payHikariConfig = payHikariConfig();
        HikariDataSource source = new HikariDataSource(payHikariConfig);
        return source;
    }

    /**
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("acqDataSource") DataSource acqDataSource,
                                        @Qualifier("payDataSource") DataSource payDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put(DatabaseType.ACQ, acqDataSource);
        targetDataSources.put(DatabaseType.PAY, payDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        DynamicDataSource.setDatabaseType(DatabaseType.ACQ);
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(acqDataSource);// acqDataSource
        return dataSource;
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dataSource) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(dataSource);// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));// 指定基包
        fb.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));//

        return fb.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }
}
