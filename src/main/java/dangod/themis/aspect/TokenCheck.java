package dangod.themis.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TokenCheck {
    @Pointcut("execution(public dangod.themis.service.impl..*. * *ByToken(..))")
    public void ServiceCheck(){};


}
