package com.ojj.persistence;

import static org.junit.Assert.fail;  
// 한단계 한단계 테스트 할건데 

import java.sql.Connection;

import javax.sql.DataSource;
// 주입받은 데이터 소스 사용하기위해

import org.apache.ibatis.session.SqlSession;
// 세션 객체 만들기 위해
import org.apache.ibatis.session.SqlSessionFactory;
//마이 바티스 세션공장 만들어야하고

import org.junit.Test;
//단계별 테스트 하기위한 
import org.junit.runner.RunWith;
// 실행 과정 보면서 테스트 위한

import org.springframework.beans.factory.annotation.Autowired;
// 어노테이션을 사용해 주입 할건데 그중 Autowired 방식

import org.springframework.test.context.ContextConfiguration;
//환경절정 root-context.xml 파일 가져와야하는데 test용도로 갖고오기위해
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


// 클래스 선언부
@RunWith(SpringJUnit4ClassRunner.class)
// 실행되는동안 과정보기위해 
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// 어노테이션을 사용해 xml문서를 주입
@Log4j
// 테스트 할때 마다 로그값 찍어주기위한 어노테이션
public class DataSourceTests {
	
	@Setter(onMethod_ = { @Autowired })
	private DataSource dataSource;
	
	@Setter(onMethod_ = { @Autowired })
	private SqlSessionFactory sqlSessionFactory;
	
	// 이 메서드를 실행할때 단위 테스트를 실행하겠다고 알려줌
	
	@Test
	public void testMyBatis() {
		
		try (SqlSession session  = sqlSessionFactory.openSession();
				Connection con = session.getConnection();) {
			
			log.info(session);
			log.info(con);
								
				
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	// 실제 접속을 하기위한 메서드.
	public void testConnection() {
		
		try (Connection con = dataSource.getConnection()) {
			
			log.info(con);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
}


