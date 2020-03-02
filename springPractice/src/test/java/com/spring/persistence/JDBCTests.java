package com.spring.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

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
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "javauser", "java1234")){
			log.info(con);
			log.info("------------------");
			log.info("데이터베이스에 정상적으로 연결되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
