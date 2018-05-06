package dangod.themis.controller.club;

import dangod.themis.controller.base.BaseController;
import dangod.themis.core.result.Result;
import dangod.themis.model.vo.club.ApplicationVo;
import dangod.themis.service.club.ApplicationService;
import dangod.themis.service.club.ApproveService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static dangod.themis.controller.base.constant.Message.CLUB_APP_NOT_FOUND;
import static dangod.themis.controller.base.constant.Message.CLUB_APP__SUCCESS_MESSAGE;
import static dangod.themis.controller.base.constant.Status.NOT_FIND;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


@CrossOrigin
@RestController
@RequestMapping(value = "/club")
public class ClubFormController extends BaseController{
    @Autowired
    private ApproveService approveService;
    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(value = "/id", method = GET)
    @ApiOperation(value = "通过id获取申请表内容")
    public String getApplication(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam("id")Long id){
        ApplicationVo applicationVo = applicationService.getApplicationVo(id);
        if(applicationVo == null) return Result.send(NOT_FIND, null, CLUB_APP_NOT_FOUND);
        return Result.send(SUCCESS, applicationVo, CLUB_APP__SUCCESS_MESSAGE);
    }

    public String apply(HttpServletRequest request, HttpServletResponse response){
        applicationService.apply(1,"硬核测试","电脑", "111","222", "22", 0,"就是很硬核的修bug",null);
        return null;
    }

    @RequestMapping(value = "/2")
    public String approve(HttpServletRequest request, HttpServletResponse response){
        approveService.approve(1,7, 1, "修去吧");
        return null;
    }
}
