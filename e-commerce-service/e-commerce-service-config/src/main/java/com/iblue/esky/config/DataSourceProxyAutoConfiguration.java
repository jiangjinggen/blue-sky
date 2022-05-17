package com.iblue.esky.config;

import javax.sql.DataSource;

/**
 * <h1>Seata 所需要的数据源代理配置类</h1>
 * */
//@Configuration
public class DataSourceProxyAutoConfiguration {

    /*private final DataSourceProperties dataSourceProperties;

    public DataSourceProxyAutoConfiguration(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }*/

    /**
     * <h2>配置数据源代理, 用于 Seata 全局事务回滚</h2>
     * before image + after image -> undo_log
     * */
    /*@Primary
    @Bean("dataSource")
    public DataSource dataSource() {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());

        return new DataSourceProxy(dataSource);
    }*/
}
