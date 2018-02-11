package dangod.themis.controller;

import dangod.themis.core.result.Result;
import dangod.themis.model.po.User;
import dangod.themis.service.TokenService;
import dangod.themis.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin
@RestController
@RequestMapping(value = "/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = POST)
    @ApiOperation(value = "登录")
    public String login(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam("username")String username,
                        @RequestParam("password")String password){
        User user = userService.check(username, password);
        if(user == null){
            return Result.send(401, null, "账号密码错误");
        }
        String token = tokenService.createToken(user.getId());
        return Result.ok(token, "返回token");
    }

    @RequestMapping(value = "", method = DELETE)
    @ApiOperation(value = "登出")
    public String login(HttpServletRequest request, HttpServletResponse response){
        String authorization = request.getHeader("Authorization");
        tokenService.deleteToken(authorization);
        return Result.ok(null, "登出成功");
    }
}
