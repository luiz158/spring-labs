package savings.repository.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

// TODO #1 mark as aspect component
public class RepositoryPerformanceMonitor {

    private static final Logger log = LoggerFactory.getLogger(RepositoryPerformanceMonitor.class);

    // TODO #2 declare this method as a named pointcut that selects executions of all repository methods
    public void anyRepositoryMethodExecution() {}

    // TODO #3 declare this method as an around advice on the above pointcut
    public Object monitor(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch(nameOf(joinPoint));
        stopWatch.start();
        try {
            // TODO #4 execute the advised method
            return null;
        } finally {
            stopWatch.stop();
            log.info(stopWatch.shortSummary());
        }
    }

    private String nameOf(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        return signature.getDeclaringType().getSimpleName() + "." + signature.getName();
    }

}
