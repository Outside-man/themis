package dangod.themis.controller.base.aspect;

import dangod.themis.core.result.Result;
import dangod.themis.model.po.score.DutyManager;
import dangod.themis.service.AuthorityService;
import dangod.themis.service.DutyManagerService;
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
import java.util.List;

import static dangod.themis.controller.base.constant.AnnotationConstant.AUTHORIZATION;
import static dangod.themis.controller.base.constant.Message.BASE_MESSAGE_SERVER_ERROR;
import static dangod.themis.controller.base.constant.Message.PERMISSIN_DENIED_MESSAGE;
import static dangod.themis.controller.base.constant.Message.TOKEN_INVAILD_MESSAGE;
import static dangod.themis.controller.base.constant.Status.*;

@Component
@Aspect
@Order(-80)
public class ScoreAuthCheck {
    @Autowired
    private DutyManagerService dutyManagerService;

    @Pointcut("@annotation(dangod.themis.controller.base.annotation.score.Major)")
    public void MajorManager() {
    }
    @Pointcut("@annotation(dangod.themis.controller.base.annotation.score.Class)")
    public void ClassManager() {
    }

    @Around("MajorManager()")
    public Object MajorManagerCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            HttpServletRequest request = ParamUtil.getRequest(proceedingJoinPoint.getArgs());
            HttpServletResponse response = ParamUtil.getResponse(proceedingJoinPoint.getArgs());
            long userId = (long) request.getAttribute("userId");
            DutyManager duty = dutyManagerService.getManagerByUserId(userId);
            if(duty==null || duty.getMajor() == null) return Result.send(PERMISSIN_DENIED, null, PERMISSIN_DENIED_MESSAGE);
            request.setAttribute("majorId", duty.getMajor().getId());
        }catch (Exception e){
            return Result.send(SERVER_ERROR, null, BASE_MESSAGE_SERVER_ERROR);

        }
        return proceedingJoinPoint.proceed();
    }

    @Around("ClassManager()")
    public Object ClassManagerCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            HttpServletRequest request = ParamUtil.getRequest(proceedingJoinPoint.getArgs());
            HttpServletResponse response = ParamUtil.getResponse(proceedingJoinPoint.getArgs());
            long userId = (long) request.getAttribute("userId");
            DutyManager duty = dutyManagerService.getManagerByUserId(userId);
            if(duty==null || duty.getaClass() == null) return Result.send(PERMISSIN_DENIED, null, PERMISSIN_DENIED_MESSAGE);
            request.setAttribute("classId", duty.getaClass().getId());
        }catch (Exception e){
            return Result.send(SERVER_ERROR, null, BASE_MESSAGE_SERVER_ERROR);
        }
        return proceedingJoinPoint.proceed();
    }
}
