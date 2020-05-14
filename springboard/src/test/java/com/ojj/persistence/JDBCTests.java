package com.ojj.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

//순수 자바 JDBC로 접속이 되는지 확인하기 위한 테스트
@Log4j
public class JDBCTests {
		
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testConnection() {
		
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
														"ojjang",
														"tiger")){
			
			log.info(con);
						
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
}
