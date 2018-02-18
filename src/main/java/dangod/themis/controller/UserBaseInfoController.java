package dangod.themis.controller;

import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.service.UserInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static dangod.themis.core.config.constant.Constant.AUTHORIZATION;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin
@RestController
@RequestMapping(value = "/baseinfo")
public class UserBaseInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(method = GET)
    @ApiOperation(value = "获取基本账户信息")
    @Authorization
    public String listMenu(HttpServletRequest request, HttpServletResponse response,
                           @RequestHeader(AUTHORIZATION)String token){

//        System.out.println("Authorization: "+TokenUtil.getUserId(token));
//        return JSON.toJSONString( userInfoService.getBaseInfoByUserId(TokenUtil.getUserId(token)));
        return "ahahah";
    }
}
