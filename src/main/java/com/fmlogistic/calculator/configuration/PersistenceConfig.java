package com.fmlogistic.calculator.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * БД конфигурации
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.fmlogistic.calculator.repository"
)
public class PersistenceConfig {

    @Bean
    public DataSource dataSource(Environment env) {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(env.getRequiredProperty("spring.datasource.url"));
        hikariConfig.setUsername(env.getRequiredProperty("spring.datasource.username"));
        hikariConfig.setPassword(env.getRequiredProperty("spring.datasource.password"));
        hikariConfig.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));
        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(Environment env, DataSource dataSource) {
        var localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.fmlogistic.calculator.entity");
        localContainerEntityManagerFactoryBean.setJpaProperties(properties(env));
        return localContainerEntityManagerFactoryBean;
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        var jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

    private Properties properties(Environment env) {
        var properties = new Properties();
        properties.setProperty("hibernate.show_sql", env.getRequiredProperty("spring.jpa.show-sql"));
        properties.setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
        properties.setProperty("hibernate.dialect", env.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
        properties.setProperty("hibernate.format_sql", env.getRequiredProperty("spring.jpa.properties.hibernate.format_sql"));
        properties.setProperty("hibernate.order_inserts", env.getRequiredProperty("spring.jpa.properties.hibernate.order_inserts"));
        properties.setProperty("hibernate.jdbc.batch_size", env.getRequiredProperty("spring.jpa.properties.hibernate.jdbc.batch_size"));
        return properties;
    }
}