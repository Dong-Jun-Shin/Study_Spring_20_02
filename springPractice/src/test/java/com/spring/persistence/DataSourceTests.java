package com.spring.persistence;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

/* 
 * @RunWith로 스프링 어플리케이션 컨텍스트가 초기화 되야하기 때문에 SpringJUnit4ClassRunner를 러너로 설정하고, 
 * @ContextConfiguration 을 통해 컨텍스트 설정 파일을 지정해줘야 한다.
 * 
 */

// @RunWith에 Runner클래스를 설정하면 JUnit에 내장된 Runner대신 그 클래스를 실행한다. 
// 여기서는 스프링 테스트를 위해서 SpringJUnit4ClassRunner라는 Runner 클래스를 설정해 준 것.
// 어플리케이션 컨텍스트를 초기 한번만 로딩. (각각의 테스트 별로 오브젝트가 생성 되더라도 싱글톤의 ApplicationContext를 보장)
@RunWith(SpringJUnit4ClassRunner.class)

/* @SpringApplicationConfiguration과 @contextConfiguration 비교 */
//Spring Application을 사용하여 스프링 애플리케이션 컨텍스트를 로드하고 처리를 수행한다. 
//이 작업에는 외부 프로퍼티 로딩과 스프링 부트 로깅도 포함된다. 
//스프링부트에서 지원하는 기능을 제대로 활용하여 테스트 하기 위해서 필요
//@SpringApplicationConfiguration은 Spring Boot에서 class형태의 애플리케이션 컨텍스트를 로딩 할 수 있는것 같다.
//@SpringApplicationConfiguration(classes = App.class)

// 테스트에서 설정파일을 읽을 수 없기 때문에, spring bean 메타 설정 파일의 위치를 지정할 때 사용
//@ContextConfiguration(locations="applicationContext.xml")
//@ContextConfiguration(classes=App.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")

@Log4j
public class DataSourceTests {
	/*
		@Setter는 setter메서드를 만들어주는 역할. setDataSource라는 메서드가 생성.
		onMethod속성은 셋터메서드를 만들때 해당 어노테이션을 지정. (버전에 따라, _가 들어간다.)
		@Autowired에 의해 해당타입에 맞는 스프링빈을 주입.
		
		아래와 같은 결과가 생성된다.
    */
	
//	@Autowired
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
	@Setter(onMethod_ = @Autowired)
	private DataSource dataSource;
	
	@Setter(onMethod_ = @Autowired)
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testConnection() {
		try(Connection con = dataSource.getConnection()) {
			log.info(con);
			log.info("-----------------------------------");
			log.info("커넥션 풀을 이용하여 데이터베이스에 정상적으로 연결되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMybatis() {
		try(SqlSession session = sqlSessionFactory.openSession(); 
				Connection con = session.getConnection();){
			log.info("++++++++++++++++++++++++++++++");
			log.info(session);
			log.info(con);
			log.info("++++++++++++++++++++++++++++++");
			log.info("Mybatis 연동 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
