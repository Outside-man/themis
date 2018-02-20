package dangod.themis.controller.base.aspect;

import dangod.themis.core.result.Result;
import dangod.themis.service.TokenService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

import static dangod.themis.controller.base.constant.Message.TOKEN_INVAILD_MESSAGE;
import static dangod.themis.controller.base.constant.Status.UNAUTHORIZED;
import static dangod.themis.controller.base.constant.AnnotationConstant.AUTHORIZATION;

@Component
@Aspect
@Order(-99)
public class TokenCheck {

    @Pointcut("@annotation(dangod.themis.controller.base.annotation.Authorization)")
    public void RequestCheck() {
    }

    @Autowired
    private TokenService tokenService;

    @Around("RequestCheck()")
    public Object tokenValid(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
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

        String token = request.getHeader(AUTHORIZATION);

        if (!tokenService.checkToken(token)) {
            return Result.send(UNAUTHORIZED, null, TOKEN_INVAILD_MESSAGE);
        }

        byte[] bytes = Base64.getDecoder().decode(token);
        String[] arr = new String(bytes, "utf-8").split("-");
        request.setAttribute("userId", arr[0]);
        return proceedingJoinPoint.proceed();
    }


}
