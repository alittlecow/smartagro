package com.sywl.config;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by sunc on 2017/4/11.
 */
@Configuration
@ConditionalOnClass({ EnableTransactionManagement.class})
@AutoConfigureAfter({ DataSourceConfiguration.class })
@MapperScan(basePackages={"com.sywl.web.dao"})
public class MybatisConfiguration implements EnvironmentAware {

    private static Log logger = LogFactory.getLog(MybatisConfiguration.class);

    private RelaxedPropertyResolver propertyResolver;

    @Resource(name="baseDataSource")
    private DataSource baseDataSource;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment,"mybatis.");
    }

    @ConditionalOnMissingBean
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() {
        try {
            SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(baseDataSource);
            sessionFactory.setTypeAliasesPackage(propertyResolver
                    .getProperty("typeAliasesPackage"));
            sessionFactory
                    .setMapperLocations(new PathMatchingResourcePatternResolver()
                            .getResources(propertyResolver
                                    .getProperty("mapperLocations")));
            sessionFactory
                    .setConfigLocation(new DefaultResourceLoader()
                            .getResource(propertyResolver
                                    .getProperty("configLocation")));

            return sessionFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("Could not confiure mybatis session factory");
            return null;
        }
    }


    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(baseDataSource);
    }

}
