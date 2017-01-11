package uk.co.mailrelay.aspect;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import uk.co.mailrelay.model.Response;

/**
 * @author Stephen Cathcart
 *
 */
@Aspect
@Component
public class LoggingAspect {

	private static final Logger logger = Logger.getLogger(LoggingAspect.class);

	@Around("execution(* uk.co.mailrelay.service..*(..))")
	public Response logAroundServices(ProceedingJoinPoint joinPoint) throws Throwable {
		Response response = null;
		logger.info("hijacked method : " + joinPoint.getSignature().getName());
		logger.info("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));
		logger.info(joinPoint.getSignature().getName() + " is running");
		Object returnValue = joinPoint.proceed();
		if (returnValue instanceof Response) {
			response = (Response) returnValue;
		}
		logger.info("returned value is : " + response);
		logger.info(joinPoint.getSignature().getName() + " is complete");
		logger.info("******");
		return response;
	}

	@AfterReturning(pointcut = "execution(* uk.co.mailrelay.exception..*(..))", returning = "returnValue")
	public void logAroundExceptions(JoinPoint joinPoint, Response returnValue) {
		logger.info("hijacked method : " + joinPoint.getSignature().getName());
		logger.error("returned value is : " + returnValue);
		logger.info("******");
	}
}
