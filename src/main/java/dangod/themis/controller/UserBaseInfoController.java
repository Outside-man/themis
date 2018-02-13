package dangod.themis.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin
@RestController
@RequestMapping(value = "/baseinfo")
public class UserBaseInfoController {


    @RequestMapping(method = GET)
    @ApiOperation(value = "获取基本账户信息")
    public String listMenu(HttpServletRequest request, HttpServletResponse response,
                           @RequestHeader("Token")String token){
        return null;
    }
}
