package dangod.themis.service;

import dangod.themis.model.vo.StudentBaseInfoVo;

import java.util.List;

public interface StudentBaseInfoService {
    StudentBaseInfoVo getStudentBaseById(long id);

    StudentBaseInfoVo getStudentBaseByUserId(long userId);

    StudentBaseInfoVo getStudentBaseBySchoolId(String schoolId);

    List<StudentBaseInfoVo> getStudentListBaseByDormitory(long dormitoryId, Integer page, Integer size);

    List<StudentBaseInfoVo> getStudentListBaseByClass(long classId, Integer page, Integer size);

    List<StudentBaseInfoVo> getStudentListBaseByMajor(long majorId, Integer page, Integer size);
}
