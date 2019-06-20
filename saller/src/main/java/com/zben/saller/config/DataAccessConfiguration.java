package com.zben.saller.config;

import com.zben.entity.Order;
import com.zben.saller.repository.OrderRepository;
import com.zben.saller.backuprepository.VerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: zben
 * @Description:数据库相关配置
 * @Date: 11:02 2019/6/20
 */
@Configuration
public class DataAccessConfiguration {

    @Autowired
    private JpaProperties jpaProperties;

    @Bean
    @ConfigurationProperties("spring.datasource.primary")
    @Primary
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.backup")
    public DataSource backupDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("primaryDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages(Order.class)
                .properties(getVendorProperties(dataSource))
                .persistenceUnit("primary")
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean backupEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("backupDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages(Order.class)
                .properties(getVendorProperties(dataSource))
                .persistenceUnit("backup")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager primaryTransactionManager(@Qualifier("primaryEntityManagerFactory") LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(primaryEntityManagerFactory.getObject());
        return transactionManager;
    }

    @Bean
    public PlatformTransactionManager backupTransactionManager(@Qualifier("backupEntityManagerFactory") LocalContainerEntityManagerFactoryBean backupEntityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(backupEntityManagerFactory.getObject());
        return transactionManager;
    }

    @Configuration
    @EnableJpaRepositories(basePackageClasses = OrderRepository.class,
            entityManagerFactoryRef = "primaryEntityManagerFactory", transactionManagerRef = "primaryTransactionManager")
    public class primaryConfiguration {
    }

    @Configuration
    @EnableJpaRepositories(basePackageClasses = VerificationRepository.class,
            entityManagerFactoryRef = "backupEntityManagerFactory", transactionManagerRef = "backupTransactionManager")
    public class backupConfiguration {
    }

    protected Map<String, Object> getVendorProperties(DataSource dataSource) {
        Map<String, Object> vendorProperties = new LinkedHashMap<String, Object>();
        vendorProperties.putAll(jpaProperties.getHibernateProperties(dataSource));
        return vendorProperties;
    }
}
