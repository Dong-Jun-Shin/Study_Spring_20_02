package com.spring.common.log;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.spring.client.board.vo.BoardVO;

import lombok.extern.log4j.Log4j;

@Log4j
@Component	//스프링에서 빈(bean)으로 인식하기 위해서 사용
@Aspect		//해당 클래스의 객체가 Aspect를 구현한 것임으로 나타내기 위해서 사용
public class LoggerAdvice {

	//	@Before("execution(* com.spring..*Impl.*(..))") 
	//	- 무엇이든 반환, com.spring.의 패키지에 해당하는 Impl로 끝나는 클래스의 모든 타입 매개변수를 받는 메서드 실행 전
	//@Before("execution(* com.spring..*Impl.*List(..))") 
	//	- 무엇이든 반환, com.spring.의 패키지에 해당하는 Impl로 끝나는 클래스의 모든 타입 매개변수를 받는 List로 끝나는 메서드 실행 전
	//@Before("execution(* com.spring..*Impl.*(..)) && args(bvo)") 
	//	- 무엇이든 반환, com.spring.의 패키지에 해당하는 Impl로 끝나는 클래스의 bvo를 매개변수로 갖는 메서드 실행 전
	
	// execution(@execution) 메서드를 기준으로 Pointcut을 설정
	// * - 반환형(제네릭처럼 모든 타입 반환가능을 명시)
	// com.spring.. - 패키지 경로
	// *Impl - 클래스명
	// *(..) - 메서드명 및 매개변수
	
	// 표현식
	@Before("execution(* com.spring..*Impl.*(..))")
	public void printLogging() {
		log.info("---------------------------------------------");
		log.info("[공통 로그 Log] 비즈니스 로직 수행 전 동작");
		log.info("---------------------------------------------");
	}
	
	// 매개변수를 포함하는 표현식
	@Before("execution(* com.spring..*Impl.*Detail(..)) && args(bvo)") 
	public void printLogging(BoardVO bvo) {
		log.info("---------------------------------------------");
		log.info("BoardVO 타입의 bvo 파라미터 값 : " + bvo);
		log.info("---------------------------------------------");
	}
	
	// JoinPoint를 이용한 호출 메서드 정보 출력
	@Before("execution(* com.spring..*Impl.*(..))") 
	public void printLogging(JoinPoint jp) {
		log.info("---------------------------------------------");
		//getSignature() : 실행하는 대상 객체에 대한 정보를 알아낼 때 사용
		log.info("[호출 메서드명] " + jp.getSignature().getName());
		//getArgs() : 전달되는 모든 파라미터들을 Object의 배열로 반환
		log.info("[호출 메서드의 파라미터 값] " + Arrays.toString(jp.getArgs()));
		log.info("---------------------------------------------");
	}
	
	// 예외가 발생한 시점에 동작하는 어노테이션
	@AfterThrowing(pointcut = "execution(* com.spring..*Impl.*(..))", throwing = "exception") 
	public void exceptionLogging(JoinPoint jp, Throwable exception) {
		log.info("---------------------------------------------");
		log.info("[예외발생]");
		log.info("[예외발생 메서드명] " + jp.getSignature().getName());
		log.info("[예외 메시지] " + exception);
		log.info("---------------------------------------------");
	}

	// 비즈니스 로직 메서드가 정상적으로 수행된 후 동작
	@AfterReturning(pointcut = "execution(* com.spring..*Impl.*(..))", returning = "returnValue") 
	public void afterReturningMethod(JoinPoint jp, Object returnValue) {
		log.info("---------------------------------------------");
		log.info("[공통 로그 Log] 비즈니스 로직 수행 후 동작");
		log.info("afterReturningMethod() called..... " + jp.getSignature().getName());
		log.info("[리턴 결과] " + returnValue);
		log.info("---------------------------------------------");
	}
	
	// 비즈니스 로직이 수행되는 시간 측정
	@Around("execution(* com.spring..*Impl.*(..))")
	public Object timeLogging(ProceedingJoinPoint pjp) throws Throwable{
		log.info("---------------------------------------------");
//		long startTime = System.currentTimeMillis();
		StopWatch watch = new StopWatch();
		watch.start();
		log.info(Arrays.deepToString(pjp.getArgs()));
		
		Object result = null;
		// proceed() : 실제 target 객체의 메서드를 실행하는 기능.
		result = pjp.proceed();
		
//		long endTime = System.currentTimeMillis();
		watch.stop();
		
		log.info("Class:" + pjp.getTarget().getClass());
//		logger.info(pjp.getSignature().getName() + ": 소요시간 " + (endTime - startTime) + "ms");
		log.info(pjp.getSignature().getName() + ": 소요시간 " + watch.getTotalTimeSeconds() + "ms");
		log.info("---------------------------------------------");
		
		return result;
	}
}
