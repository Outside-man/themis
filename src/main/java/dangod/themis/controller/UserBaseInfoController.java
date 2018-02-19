package dangod.themis.controller;

import dangod.themis.controller.base.BaseController;
import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.core.result.Result;
import dangod.themis.model.po.UserBaseInfo;
import dangod.themis.model.vo.UserBaseInfoVo;
import dangod.themis.service.UserInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static dangod.themis.controller.base.constant.Message.BASEINFO_FAIL_MESSAGE;
import static dangod.themis.controller.base.constant.Message.BASEINFO_SUCCESS_MESSAGE;
import static dangod.themis.controller.base.constant.Status.FAIL;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static dangod.themis.core.config.constant.Constant.AUTHORIZATION;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin
@RestController
@RequestMapping(value = "/baseinfo")
public class UserBaseInfoController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(method = GET)
    @ApiOperation(value = "获取基本账户信息")
    @Authorization
    public String getBaseInfo(HttpServletRequest request, HttpServletResponse response,
                           @RequestHeader(AUTHORIZATION)String token){
        UserBaseInfoVo baseInfoVo = userInfoService.getBaseInfoByUserId(getUserId(request));
        if(baseInfoVo == null)
            return Result.send(FAIL, null, BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, baseInfoVo, BASEINFO_SUCCESS_MESSAGE);
    }
}
