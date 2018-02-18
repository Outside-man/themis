package dangod.themis.controller;

import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.controller.base.annotation.ContainAuthority;
import dangod.themis.core.result.Result;
import dangod.themis.model.po.User;
import dangod.themis.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static dangod.themis.controller.base.constant.Message.REGISTER_FAIL_MESSAGE;
import static dangod.themis.controller.base.constant.Message.REGISTER_SUCCESS_MESSAGE;
import static dangod.themis.controller.base.constant.Status.FAIL;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static dangod.themis.core.config.constant.Constant.AUTHORIZATION;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = POST)
    @ApiOperation(value = "注册")
    public String register(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam("username")String username,
                           @RequestParam("password")String password){
        User user = userService.add(username, password);
        if(user == null)
            return Result.send(FAIL, null, REGISTER_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, REGISTER_SUCCESS_MESSAGE);
    }

    @RequestMapping(method = PUT)
    @ApiOperation(value = "修改密码")
    @ContainAuthority(1)
    @Authorization
    public String register(HttpServletRequest request, HttpServletResponse response){


//
//
//        User user = userService.add(username, password);
//        if(user == null)
//            return Result.send(FAIL, null, REGISTER_FAIL_MESSAGE);
//        return Result.send(SUCCESS, null, REGISTER_SUCCESS_MESSAGE);
        return null;
    }
}
