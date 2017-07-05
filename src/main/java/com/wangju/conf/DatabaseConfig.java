package com.wangju.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by java_zong on 2017/7/5.
 */
@Configurable
@EnableTransactionManagement
public class DatabaseConfig {
    private static final Logger logger= LoggerFactory.getLogger(DatabaseConfig.class);

    @Value("${jdbc.driver}")
    private String jdbcDriver;

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Value("${db.maxtotal}")
    private Integer maxTotal;

    @Value("${db.minidle}")
    private Integer minIdle;


    @Bean
    public DataSource dataSource(){
        logger.info("mysql url:"+dbUrl);
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(jdbcDriver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(maxTotal);
        dataSource.setMinIdle(minIdle);
        return dataSource;

    }

    /**
     * 事务管理
     * @return
     */
    @Bean
    public DataSourceTransactionManager txManager(){
          return new DataSourceTransactionManager(dataSource());
    }

    /**
     * sqlsession会话
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory()throws Exception{
        SqlSessionFactoryBean sessionFactory=new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        return sessionFactory.getObject();
    }


}
