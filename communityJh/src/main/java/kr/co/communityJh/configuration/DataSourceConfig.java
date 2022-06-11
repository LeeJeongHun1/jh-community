package kr.co.communityJh.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = "kr.co.communityJh.mapper")
public class DataSourceConfig {

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource,
			ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setMapperLocations(
				applicationContext.getResources("classpath:myBatis/mappers/*.xml"));
		factoryBean.setConfigLocation(applicationContext.getResource("classpath:myBatis/myBatis-config.xml"));
//		factoryBean.setTypeAliasesPackage("kr.co.zaritalk.vo");
		factoryBean.setDataSource(dataSource);

		return factoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate db1SqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
