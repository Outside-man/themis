package dangod.themis.controller.club;

import com.alibaba.fastjson.JSON;
import dangod.themis.controller.base.BaseController;
import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.controller.base.annotation.ContainAuthority;
import dangod.themis.controller.base.annotation.club.Club;
import dangod.themis.controller.base.annotation.club.ClubAdmin;
import dangod.themis.core.result.Result;
import dangod.themis.model.vo.club.ApplicationVo;
import dangod.themis.model.vo.club.StatusVo;
import dangod.themis.service.club.ApplicationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static dangod.themis.controller.base.constant.AnnotationConstant.AUTHORIZATION;
import static dangod.themis.controller.base.constant.Message.*;
import static dangod.themis.controller.base.constant.Status.FAIL;
import static dangod.themis.controller.base.constant.Status.NOT_FIND;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static dangod.themis.model.po.authority.constant.TypeContant.CLUB_ACTIVITY_APPLY;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@CrossOrigin
@RestController
@RequestMapping(value = "/club/app")
public class ClubAppController extends BaseController{
    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(method = GET)
    @ApiOperation(value = "通过id获取申请表内容")
    public String getApplication(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam("id")Long id){
        ApplicationVo applicationVo = applicationService.getApplicationVo(id);
        if(applicationVo == null) return Result.send(NOT_FIND, null, CLUB_APP_NOT_FOUND);
        return Result.send(SUCCESS, applicationVo, CLUB_APP_MESSAGE);
    }

    @RequestMapping(method = POST)
    @ApiOperation(value = "社团申请活动")
    @Authorization
    @ContainAuthority(CLUB_ACTIVITY_APPLY)
    @Club
    public String apply(HttpServletRequest request, HttpServletResponse response,
                        @RequestHeader(AUTHORIZATION)String token,
                        @RequestParam("name")String name,
                        @RequestParam("place")String place,
                        @RequestParam("start")String start,
                        @RequestParam("end")String end,
                        @RequestParam("people")String people,
                        @RequestParam("isFile")Integer isFile,
                        @RequestParam("introduce")String introduce,
                        @RequestParam("file") MultipartFile file){
        int status = applicationService.apply(getClub(request), name, place, start, end, people, isFile, introduce, file);
        if(status != 0) return Result.send(FAIL, null, CLUB_APP_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, CLUB_APP_SUCCESS_MESSAGE);
    }
}
