package dangod.themis.controller.score;

import dangod.themis.controller.base.BaseController;
import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.controller.base.annotation.ContainAuthority;
import dangod.themis.core.result.Result;
import dangod.themis.model.vo.score.StudentBaseInfoVo;
import dangod.themis.model.vo.score.record.ActivityVo;
import dangod.themis.service.StudentRecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static dangod.themis.controller.base.constant.AnnotationConstant.AUTHORIZATION;
import static dangod.themis.controller.base.constant.Message.STU_BASEINFO_FAIL_MESSAGE;
import static dangod.themis.controller.base.constant.Message.STU_BASEINFO_SUCCESS_MESSAGE;
import static dangod.themis.controller.base.constant.Status.FAIL;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static dangod.themis.model.po.authority.constant.TypeContant.SELF_STUDENT;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
@CrossOrigin
@RestController
@RequestMapping(value = "/stu/activity")
public class StuActivityController extends BaseController{
    @Autowired
    private StudentRecordService recordService;

    @RequestMapping(method = GET)
    @ApiOperation(value = "用户获取学生活动信息信息(全部)")
    @ContainAuthority(SELF_STUDENT)
    @Authorization
    public String getStudentActivity(HttpServletRequest request, HttpServletResponse response,
                                     @RequestHeader(AUTHORIZATION)String token,
                                     @RequestParam("page")Integer page){
        List<ActivityVo> list = recordService.getActivityByUserId(getUserId(request), page, 5);
        if(list == null)
            return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, STU_BASEINFO_SUCCESS_MESSAGE);
    }
}
