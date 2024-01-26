package com.tamilcreations.estorespringboot.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.tamilcreations.estorespringboot.utils.Utils;

@Aspect
@Component
public class LoggingAspect
{
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Pointcut("within(com.tamilcreations.estorespringboot..*) && !within(com.tamilcreations.estorespringboot.aspect..*) && !within(com.tamilcreations.estorespringboot.security..*) && !within(com.tamilcreations.estorespringboot.users..*)")
	public void withinPackageWithoutAspect()
	{
	}

//    @Pointcut("execution(public com.tamilcreations.estorespringboot.products.feedbacks.ProductFeedbackResponse com.tamilcreations.estorespringboot.products.feedbacks.ProductFeedbackController.addNewProductFeedback(..))")
//    public void withinPackageWithoutAspect() {}

	@Pointcut("withinPackageWithoutAspect() && execution(* *(..))")
	public void allPublicMethodsInPackageWithoutAspect()
	{
	}

	@Before(value = "allPublicMethodsInPackageWithoutAspect()")
	public void doBeforeMethods(JoinPoint joinPoint)
	{
		String correlationId = MDC.get("correlationId");
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getName();
		logger.info("CorrelationId:" + correlationId + " : Entering method: {}.{} " + correlationId, className,
				methodName);
		// System.out.println(correlationId+" Entering method: {}.{} "+ className+"."+
		// methodName);
	}

	@After(value = "allPublicMethodsInPackageWithoutAspect()")
	public void doAfterMethods(JoinPoint joinPoint)
	{
		String correlationId = MDC.get("correlationId");
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getName();
		logger.info("CorrelationId:" + correlationId + " : Exiting method: {}.{} " + correlationId, className,
				methodName);
		// System.out.println(correlationId+" Exiting method: {}.{} "+ className+"."+
		// methodName);
	}

	@AfterReturning(pointcut = "allPublicMethodsInPackageWithoutAspect()", returning = "retVal")
	public void doAfterMethodReturnning(JoinPoint joinPoint, Object retVal)
	{
		String correlationId = MDC.get("correlationId");
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getName();
		logger.info("CorrelationId:" + correlationId + " : After returning value in the method: {}.{} ", className,
				methodName);
		// System.out.println(correlationId+" - Exiting method - After Returning Value:
		// {}.{} "+ className+"."+ methodName);
	}

	@AfterThrowing(pointcut = "allPublicMethodsInPackageWithoutAspect()", throwing = "ex")
	public void doAfterMethodThrowingTask(JoinPoint joinPoint, Exception ex)
	{
		String correlationId = MDC.get("correlationId");
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getName();
		logger.info("CorrelationId:" + correlationId + " : Exiting method after throwing error: {}.{}", className,
				methodName);
		// System.out.println(correlationId+" Exiting method - After Throwing Error:
		// {}.{} "+ className+"."+ methodName);

	}

//	@Around(value = "allPublicMethodsInPackageWithoutAspect()")
//	public void doAroundTask(JoinPoint joinPoint){
//		 String correlationId = MDC.get("correlationId");
//	}


	
	@Pointcut("@annotation(org.springframework.graphql.data.method.annotation.QueryMapping)")
	private void queryMethods() {}
	
	@Before(value = "queryMethods()")
	public void doBeforeQueryMethods(JoinPoint joinPoint)
	{
		Utils.generateCorrelationId();
		String correlationId = MDC.get("correlationId");
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getName();
		logger.info("CorrelationId:" + correlationId + " : Entering @QueryMapping method: {}.{} " + correlationId, className,
				methodName);
		// System.out.println(correlationId+" Entering method: {}.{} "+ className+"."+
		// methodName);
	}
	
	@Pointcut("@annotation(org.springframework.graphql.data.method.annotation.MutationMapping)")
	private void mutationMethods() {}
	
	@Before(value = "mutationMethods()")
	public void doBeforeMutationMethods(JoinPoint joinPoint)
	{
		Utils.generateCorrelationId();
		String correlationId = MDC.get("correlationId");
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getName();
		logger.info("CorrelationId:" + correlationId + " : Entering @MutationMapping method: {}.{} " + correlationId, className,
				methodName);
		// System.out.println(correlationId+" Entering method: {}.{} "+ className+"."+
		// methodName);
	}
}
