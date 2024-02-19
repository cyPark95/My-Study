package pcy.study.simpleboard.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class TimerAop {

    @Pointcut(value = "execution(* pcy.study.simpleboard..*Controller.*(..))")
    private void controllerPointCut() {

    }

    // 대상 메서드의 실행 전과 후, 예외 발생 시 호출
    @Around("controllerPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(">>> AOP Around Start");

        var stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();
        log.info("Method Duration = {} ms", stopWatch.getTotalTimeMillis());

        log.info(">>> AOP Around End");
        return result;
    }

    // 대상 메서드 실행 전 호출
    @Before("controllerPointCut()")
    public void before() {
        log.info(">>> AOP Before");
    }

    // 대상 메서드 정상 실행 후 호출
    @AfterReturning(pointcut = "controllerPointCut()", returning = "result")
    public void afterReturning(Object result) {
        log.info(">>> AOP AfterReturning");
    }

    // 대상 메서드 예외 발생시 호출
    @AfterThrowing(pointcut = "controllerPointCut()", throwing = "ex")
    public void afterThrowing(Exception ex) {
        log.info(">>> AOP AfterThrowing");
    }

    // 대상 메서드 실행 후 호출
    // 예외 발생 여부에 관계 없이 호출
    @After("controllerPointCut()")
    public void after() {
        log.info(">>> AOP After");
    }
}
