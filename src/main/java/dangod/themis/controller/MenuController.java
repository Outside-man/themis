package dangod.themis.controller;

import dangod.themis.core.result.Result;
import dangod.themis.model.vo.MenuVo;
import dangod.themis.service.AuthorityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static dangod.themis.controller.constant.Message.MENU_SUCCESS_MESSAGE;
import static dangod.themis.controller.constant.Message.TOKEN_INVAILD_MESSAGE;
import static dangod.themis.controller.constant.Status.SUCCESS;
import static dangod.themis.controller.constant.Status.UNAUTHORIZED;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin
@RestController
@RequestMapping(value = "/menu")
public class MenuController {
    @Autowired
    private AuthorityService authorityService;

    @RequestMapping(method = POST)
    @ApiOperation(value = "获取菜单")
    public String listMenu(HttpServletRequest request, HttpServletResponse response,
                           @RequestHeader("Token")String token){
        List<MenuVo> list =  authorityService.getMenuByToken(token);

        if(list != null)
            return Result.send(SUCCESS, list, MENU_SUCCESS_MESSAGE);
        else
            return Result.send(UNAUTHORIZED, null, TOKEN_INVAILD_MESSAGE);
    }
}
