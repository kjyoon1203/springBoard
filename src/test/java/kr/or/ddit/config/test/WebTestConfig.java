package kr.or.ddit.config.test;

import javax.annotation.Resource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
	 "classpath:kr/or/ddit/config/spring/context-root.xml"
	,"classpath:kr/or/ddit/config/spring/context-datasource-test.xml"
	,"classpath:kr/or/ddit/config/spring/context-transaction.xml" 
	,"classpath:kr/or/ddit/config/spring/servlet-context.xml"
})	// controller scan: servlet-context.xml
@WebAppConfiguration	// 스프링 컨테이너를 구성할 web기반 application context로 구성
public class WebTestConfig {

	@Resource(name="datasource")
	private BasicDataSource datasource;
	
	// controller 테스트를 하기 위해 필요한 것 2가지
	// applicationContext: 스프링 컨테이너
	// MockMvc: dispatcherServlet(applicationContext객체를 통해 생성)
	
	// 주입하려고 하는 필드의 타입과 일치할 경우 이름과 관계없이 주입
	// 주입하려는 필드의 스프링 빈 중 타입이 일치하는 빈이 2개 이상 존재할 경우 에러
	@Autowired	
	private WebApplicationContext context;
	
	protected MockMvc mockMvc;
	
	// server(tomcat)가 없는 환경에서 테스트가 가능하다.
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("/kr/or/ddit/db/init.sql"));
		populator.setContinueOnError(false);	// init.sql을 실행하다 에러가 발생할 경우 중지
		DatabasePopulatorUtils.execute(populator, datasource);
	}
	
	@Ignore	// 테스트 코드를 실행하지 말고 건너뛰어라
	@Test
	public void dummy() {
		
	}
}
