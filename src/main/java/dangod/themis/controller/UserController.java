package dangod.themis.controller;

import dangod.themis.core.result.Result;
import dangod.themis.model.po.User;
import dangod.themis.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static dangod.themis.controller.constant.Message.REGISTER_FAIL_MESSAGE;
import static dangod.themis.controller.constant.Message.REGISTER_SUCCESS_MESSAGE;
import static dangod.themis.controller.constant.Status.FAIL;
import static dangod.themis.controller.constant.Status.SUCCESS;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
}
