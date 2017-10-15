package com.springboot.server.pictureserver.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import java.util.Properties;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSourceFactory;


@Configuration
@EnableTransactionManagement
@MapperScan(basePackages="com.springboot.server.pictureserver.mapper")
public class DatabaseConfig implements TransactionManagementConfigurer {

//    @Autowired 
//    DataSource dataSource;
    
    @Autowired
    private Environment env;
    
    /**
    * 创建数据源
    * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错 
    */
    @Bean
    //@Primary
    public DataSource getDataSource() throws Exception{
	    Properties props = new Properties();
	    props.put("driverClassName", env.getProperty("spring.datasource.driver-class-name"));
	    props.put("url", env.getProperty("spring.datasource.url"));
	    props.put("username", env.getProperty("spring.datasource.username"));
	    props.put("password", env.getProperty("spring.datasource.password"));
	    return DruidDataSourceFactory.createDataSource(props);
    }
    
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        //指定基包
        bean.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));
        //指定xml文件位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));
        return bean.getObject();
    }
    
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
    	DataSource dataSource = null;
    	try {
			dataSource = getDataSource();
		} catch (Exception e) {
			System.out.println("创建数据源失败");
			e.printStackTrace();
		}
		return new DataSourceTransactionManager(dataSource);
	}
} // class DatabaseConfig
