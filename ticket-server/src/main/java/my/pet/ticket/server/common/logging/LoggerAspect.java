package my.pet.ticket.server.common.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
public class LoggerAspect {

    @Pointcut("execution(public * my.pet.ticket.server.adapter.persistence..*(..))")
    public void persistenceLayer() {}

    @Around("persistenceLayer()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String logId = UUID.randomUUID().toString();
        Log.INFO(logId, DefaultEvent.DBP_START);
        Object result = proceedingJoinPoint.proceed();
        Log.INFO(logId, DefaultEvent.DBP_END);
        return result;
    }

}
