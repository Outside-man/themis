package dangod.themis.controller;

import dangod.themis.controller.base.BaseController;
import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.controller.base.annotation.ContainAuthority;
import dangod.themis.core.result.Result;
import dangod.themis.model.po.Inform;
import dangod.themis.model.po.User;
import dangod.themis.model.vo.InformVo;
import dangod.themis.service.InformService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static dangod.themis.controller.base.constant.Message.*;
import static dangod.themis.controller.base.constant.Status.FAIL;
import static dangod.themis.controller.base.constant.Status.NOT_FIND;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static dangod.themis.core.config.constant.Constant.AUTHORIZATION;
import static dangod.themis.model.po.authority.constant.TypeContant.INFORM_MANAGE;
import static dangod.themis.model.po.authority.constant.TypeContant.INFORM_SELF;
import static dangod.themis.model.po.authority.constant.TypeContant.INFORM_SEND;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin
@RestController
@RequestMapping(value = "/inform")
public class InformController extends BaseController{
    @Autowired
    private InformService informService;

    @RequestMapping(value = "/id/{id}", method = GET)
    @ApiOperation(value = "通过id获取通知")
    public String getInformById(HttpServletRequest request, HttpServletResponse response,
                                 @PathVariable("id")Long id){
        InformVo informVo = informService.getById(id);
        if(informVo == null) return Result.send(NOT_FIND, null, INFORM_NOT_EXISTS_MESSAGE);
        return Result.send(SUCCESS, informVo, INFORM_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/page/{page}", method = GET)
    @ApiOperation(value = "分页获取通知")
    public String getInformByPage(HttpServletRequest request, HttpServletResponse response,
                                    @PathVariable("page")Integer page){

        List<InformVo> list = informService.getPage(page);
        if(list == null) return Result.send(NOT_FIND, null, INFORM_NOT_EXISTS_MESSAGE);
        return Result.send(SUCCESS, list, INFORM_SUCCESS_MESSAGE);
    }

    @RequestMapping(method = POST)
    @ApiOperation(value = "发送通知")
    @Authorization
    @ContainAuthority(INFORM_SEND)
    public String getInformByUserId(HttpServletRequest request, HttpServletResponse response,
                                    @RequestHeader(AUTHORIZATION)String token,
                                    @RequestParam("title")String title,
                                    @RequestParam("content")String content){
        InformVo informVo = informService.addInform(title, content, getUserId(request));
        if(informVo == null) return Result.send(FAIL, null, INFORM_SEND_FAIL_MESSAGE);
        return Result.send(SUCCESS, informVo, INFORM_SEND_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/user", method = GET)
    @ApiOperation(value = "获取自己已发通知")
    @Authorization
    @ContainAuthority(INFORM_SELF)
    public String getInformBySelf(HttpServletRequest request, HttpServletResponse response,
                                    @RequestHeader(AUTHORIZATION)String token){

        List<InformVo> list = informService.getListByUserId(getUserId(request));
        if(list == null) return Result.send(NOT_FIND, null, INFORM_NOT_EXISTS_MESSAGE);
        return Result.send(SUCCESS, list, INFORM_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/user/{userId}", method = GET)
    @ApiOperation(value = "管理通过userId获取通知")
    @Authorization
    @ContainAuthority(INFORM_MANAGE)
    public String getInformByUserId(HttpServletRequest request, HttpServletResponse response,
                                    @RequestHeader(AUTHORIZATION)String token,
                                    @PathVariable("userId")Long userId){

        List<InformVo> list = informService.getListByUserId(userId);
        if(list == null) return Result.send(NOT_FIND, null, INFORM_NOT_EXISTS_MESSAGE);
        return Result.send(SUCCESS, list, INFORM_SUCCESS_MESSAGE);
    }
}
