package dangod.themis.controller.score;

import dangod.themis.controller.base.BaseController;
import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.controller.base.annotation.ContainAuthority;
import dangod.themis.core.result.Result;
import dangod.themis.core.util.BaseFile;
import dangod.themis.model.vo.score.file.result.ImportResult;
import dangod.themis.service.StudentBaseInfoService;
import dangod.themis.service.StudentRecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static dangod.themis.controller.base.constant.AnnotationConstant.AUTHORIZATION;
import static dangod.themis.controller.base.constant.Message.*;
import static dangod.themis.controller.base.constant.Status.FAIL;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static dangod.themis.core.util.BaseFile.FOLDER;
import static dangod.themis.model.po.authority.constant.TypeContant.DB_STU_BASE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin
@RestController
@RequestMapping(value = "/stu/db")
public class DBController extends BaseController{
    @Autowired
    private StudentBaseInfoService studentBaseInfoService;
    @Autowired
    private StudentRecordService studentRecordService;

    @RequestMapping(value = "/base/file",method = POST)
    @ApiOperation(value = "数据库管理员添加学生信息")
    @Authorization
    @ContainAuthority(DB_STU_BASE)
    public String addStudentByFile(HttpServletRequest request, HttpServletResponse response,
                                   @RequestHeader(AUTHORIZATION)String token,
                                   @RequestParam("file") MultipartFile file){
        ImportResult result = studentBaseInfoService.addStudentBaseByFile(file, getRealName(request));
        if(result == null)
            return Result.send(FAIL, null, STU_DB_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, result.getFailMessage(), String.format(STU_DB_BASEINFO_SUCCESS_MESSAGE, result.getSuccessNum(), result.getFailNum()));

    }

    @RequestMapping(value = "/base/file",method = GET)
    @ApiOperation(value = "数据库管理员下载模板文件")
    public String downloadBaseTemplate(HttpServletRequest request, HttpServletResponse response){
        int status = BaseFile.download(response, FOLDER + "templates", "stu_base_import.xlsx", "学生导入模板.xlsx");
        if(status == -1)
            return Result.send(FAIL, null, STU_DB_DOWNLOAD_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, STU_DB_DOWNLOAD_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/activity/file",method = POST)
    @ApiOperation(value = "数据库管理员添加学生信息")
    @Authorization
    @ContainAuthority(DB_STU_BASE)
    public String addActivityByFile(HttpServletRequest request, HttpServletResponse response,
                                   @RequestHeader(AUTHORIZATION)String token,
                                   @RequestParam("file") MultipartFile file){
        ImportResult result = studentRecordService.addActivityByFile(file, getRealName(request));
        if(result == null)
            return Result.send(FAIL, null, STU_DB_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, result.getFailMessage(), String.format(STU_DB_BASEINFO_SUCCESS_MESSAGE, result.getSuccessNum(), result.getFailNum()));

    }

    @RequestMapping(value = "/activity/file",method = GET)
    @ApiOperation(value = "数据库管理员下载模板文件")
    public String downloadActivityTemplate(HttpServletRequest request, HttpServletResponse response){
        int status = BaseFile.download(response, FOLDER + "templates", "stu_activity_import.xlsx", "活动导入模板.xlsx");
        if(status == -1)
            return Result.send(FAIL, null, STU_DB_DOWNLOAD_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, STU_DB_DOWNLOAD_SUCCESS_MESSAGE);
    }
}
