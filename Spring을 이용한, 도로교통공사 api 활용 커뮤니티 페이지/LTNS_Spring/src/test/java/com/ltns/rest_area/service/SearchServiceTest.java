package com.ltns.rest_area.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
	"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/jdbc/jdbc-context.xml",
	"file:src/main/webapp/WEB-INF/spring/jdbc/mybatis-context.xml"})
class SearchServiceTest {
	
	@Autowired
	SqlSession sqlSession;
	
	@Test
	void test() {
		SearchService searchService = new SearchService();
		System.out.println("sqlSession"+sqlSession);
		System.out.println(searchService.raCount());
	}

}
