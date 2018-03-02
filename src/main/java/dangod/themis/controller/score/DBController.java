package dangod.themis.controller.score;

import dangod.themis.controller.base.BaseController;
import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.controller.base.annotation.ContainAuthority;
import dangod.themis.core.result.Result;
import dangod.themis.service.StudentBaseInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static dangod.themis.controller.base.constant.AnnotationConstant.AUTHORIZATION;
import static dangod.themis.controller.base.constant.Message.STU_DB_BASEINFO_FAIL_MESSAGE;
import static dangod.themis.controller.base.constant.Message.STU_DB_BASEINFO_SUCCESS_MESSAGE;
import static dangod.themis.controller.base.constant.Status.FAIL;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static dangod.themis.model.po.authority.constant.TypeContant.DB_STU_BASE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin
@RestController
@RequestMapping(value = "/stu/db")
public class DBController extends BaseController{
    @Autowired
    private StudentBaseInfoService studentBaseInfoService;

    @RequestMapping(value = "/file",method = POST)
    @ApiOperation(value = "数据库管理员添加学生信息")
    @Authorization
    @ContainAuthority(DB_STU_BASE)
    public String addStudentByFile(HttpServletRequest request, HttpServletResponse response,
                                   @RequestHeader(AUTHORIZATION)String token,
                                   @RequestParam("file") MultipartFile file){
        int status = studentBaseInfoService.addStudentBaseByFile(file, getRealName(request));
        if(status == -1)
            return Result.send(FAIL, null, STU_DB_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, String.format(STU_DB_BASEINFO_SUCCESS_MESSAGE, status));

    }
}
