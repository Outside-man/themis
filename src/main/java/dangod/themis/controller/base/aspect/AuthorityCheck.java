package dangod.themis.controller.base.aspect;

import dangod.themis.controller.base.annotation.ContainAuthority;
import dangod.themis.core.result.Result;
import dangod.themis.model.po.authority.AuthorityMenu;
import dangod.themis.service.AuthorityService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static dangod.themis.controller.base.constant.Message.PERMISSIN_DENIED;
import static dangod.themis.controller.base.constant.Message.TOKEN_INVAILD_MESSAGE;
import static dangod.themis.controller.base.constant.Status.UNAUTHORIZED;

@Component
@Aspect
@Order(1)
public class AuthorityCheck {
    @Pointcut("@annotation(dangod.themis.controller.base.annotation.ContainAuthority)")
    public void RequestCheck() {
    }

    @Autowired
    private AuthorityService authorityService;

    @Around("RequestCheck() && @annotation(authority)")
    public Object containAuthority(ProceedingJoinPoint proceedingJoinPoint, ContainAuthority authority) throws Throwable {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        Object[] args = proceedingJoinPoint.getArgs();
        //获取请求方法的request & response
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                request = (HttpServletRequest) arg;
                request.setCharacterEncoding("UTF-8");
            }
            if (arg instanceof HttpServletResponse) {
                response = (HttpServletResponse) arg;
                response.setCharacterEncoding("UTF-8");
            }
        }
        List<Long> authorityList = authorityService.getAuthoritiesByUserId(Long.parseLong((String)request.getAttribute("userId")));
        System.out.println(authorityList);
        System.out.println(authority.value());
        if(!authorityList.contains(authority.value())){
            return Result.send(UNAUTHORIZED, null, PERMISSIN_DENIED);
        }
        return proceedingJoinPoint.proceed();
    }
}
