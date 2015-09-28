package by.academy.alekhno.database.dao.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogMethodsAspect {

	@Before("interfaceDAOMethod()")
	public void startMethodLog(JoinPoint jp) {
		Signature signature = jp.getSignature();
		Logger logger = Logger.getLogger(signature.getClass().getName());
		logger.info(signature.getName() + " is started.");
	}

	@AfterReturning("interfaceDAOMethod()")
	public void endtMethodLog(JoinPoint jp) {
		Signature signature = jp.getSignature();
		Logger logger = Logger.getLogger(signature.getClass().getName());
		logger.info(signature.getName() + " is finished.");
	}

	@AfterThrowing("interfaceDAOMethod()")
	public void endErrortMethodLog(JoinPoint jp) {
		Signature signature = jp.getSignature();
		Logger logger = Logger.getLogger(signature.getClass().getName());
		logger.info(signature.getName() + " is finished with error.");
	}

	@Pointcut("execution(* by.academy.alekhno.database.dao.interf.*.*(..))")
	public void interfaceDAOMethod() {
	}

}
