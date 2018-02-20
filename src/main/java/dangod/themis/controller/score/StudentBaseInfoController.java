package dangod.themis.controller.score;

import dangod.themis.controller.base.BaseController;
import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.controller.base.annotation.ContainAuthority;
import dangod.themis.core.result.Result;
import dangod.themis.model.vo.StudentBaseInfoVo;
import dangod.themis.service.StudentBaseInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static dangod.themis.controller.base.constant.Message.STU_BASEINFO_FAIL_MESSAGE;
import static dangod.themis.controller.base.constant.Message.STU_BASEINFO_SUCCESS_MESSAGE;
import static dangod.themis.controller.base.constant.Status.FAIL;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static dangod.themis.model.po.authority.constant.TypeContant.SCHOOL_STUDENT_MANAGE;
import static dangod.themis.model.po.authority.constant.TypeContant.SELF_STUDENT;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/stubase")
public class StudentBaseInfoController extends BaseController{
    @Autowired
    private StudentBaseInfoService studentBaseInfoService;

    @RequestMapping(method = GET)
    @ApiOperation(value = "用户获取学生信息")
    @ContainAuthority(SELF_STUDENT)
    @Authorization
    public String getStudentBaseInfo(HttpServletRequest request, HttpServletResponse response){
        StudentBaseInfoVo baseInfoVo = studentBaseInfoService.getStudentBaseByUserId(getUserId(request));
        if(baseInfoVo == null)
            return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, baseInfoVo, STU_BASEINFO_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "school/user",method = GET)
    @ApiOperation(value = "校级管理员获取学生信息(user)")
    @ContainAuthority(SCHOOL_STUDENT_MANAGE)
    @Authorization
    public String getStudentBaseInfoSchoolAdminByUser(HttpServletRequest request, HttpServletResponse response,
                                                      @RequestParam("id")long id){
        StudentBaseInfoVo baseInfoVo = studentBaseInfoService.getStudentBaseByUserId(id);
        if(baseInfoVo == null) return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, baseInfoVo, STU_BASEINFO_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "school/stuid",method = GET)
    @ApiOperation(value = "校级管理员获取学生信息(stuid)")
    @ContainAuthority(SCHOOL_STUDENT_MANAGE)
    @Authorization
    public String getStudentBaseInfoSchoolAdminBystuId(HttpServletRequest request, HttpServletResponse response,
                                                       @RequestParam("id")String id){
        StudentBaseInfoVo baseInfoVo = studentBaseInfoService.getStudentBaseBySchoolId(id);
        if(baseInfoVo == null) return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, baseInfoVo, STU_BASEINFO_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "school/class",method = GET)
    @ApiOperation(value = "校级管理员获取学生信息(class)")
    @ContainAuthority(SCHOOL_STUDENT_MANAGE)
    @Authorization
    public String getStudentBaseInfoSchoolAdminByClass(HttpServletRequest request, HttpServletResponse response,
                                                       @RequestParam("id")long id,
                                                       @RequestParam("page")Integer page){
        int size = 6;
        if(getAttribute(request, "size") != null)
            size = Integer.parseInt(getAttribute(request, "size"));
        List<StudentBaseInfoVo> list = studentBaseInfoService.getStudentListBaseByClass(id, page, size);
        if(list == null) return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, STU_BASEINFO_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "school/major",method = GET)
    @ApiOperation(value = "校级管理员获取学生信息(major)")
    @ContainAuthority(SCHOOL_STUDENT_MANAGE)
    @Authorization
    public String getStudentBaseInfoSchoolAdminByMajor(HttpServletRequest request, HttpServletResponse response,
                                                       @RequestParam("id")long id,
                                                       @RequestParam("page")Integer page){
        int size = 6;
        if(getAttribute(request, "size") != null)
            size = Integer.parseInt(getAttribute(request, "size"));
        List<StudentBaseInfoVo> list = studentBaseInfoService.getStudentListBaseByMajor(id, page, size);
        if(list == null) return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, STU_BASEINFO_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "school/dormitory",method = GET)
    @ApiOperation(value = "校级管理员获取学生信息(major)")
    @ContainAuthority(SCHOOL_STUDENT_MANAGE)
    @Authorization
    public String getStudentBaseInfoSchoolAdminByDormitory(HttpServletRequest request, HttpServletResponse response,
                                                       @RequestParam("id")long id,
                                                       @RequestParam("page")Integer page){
        int size = 6;
        if(getAttribute(request, "size") != null)
            size = Integer.parseInt(getAttribute(request, "size"));
        List<StudentBaseInfoVo> list = studentBaseInfoService.getStudentListBaseByDormitory(id, page, size);
        if(list == null) return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, STU_BASEINFO_SUCCESS_MESSAGE);
    }

}
