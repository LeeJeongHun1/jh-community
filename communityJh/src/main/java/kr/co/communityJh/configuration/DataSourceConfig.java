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
		
		// mapper xml 경로 지정 (myBatis 사용)
//		factoryBean.setMapperLocations(
//				applicationContext.getResources("classpath:myBatis/mappers/*.xml"));
		// myBatis config 환경설정 xml 경로
//		factoryBean.setConfigLocation(applicationContext.getResource("classpath:myBatis/myBatis-config.xml"));
		// mapper xml 에 typeAliases error 발생하여 현재 config xml에 기입함
//		factoryBean.setTypeAliasesPackage("kr.co.zaritalk.entity");
		
		factoryBean.setDataSource(dataSource);
		return factoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate db1SqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
