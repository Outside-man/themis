package dangod.themis.service.impl;

import com.alibaba.fastjson.JSON;
import dangod.themis.dao.score.StudentBaseInfoRepo;
import dangod.themis.model.po.score.StudentBaseInfo;
import dangod.themis.model.vo.StudentBaseInfoVo;
import dangod.themis.service.StudentBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class StudentBaseInfoServiceImpl implements StudentBaseInfoService {
    @Autowired
    private StudentBaseInfoRepo studentBaseInfoRepo;

    @Override
    public StudentBaseInfoVo getStudentBaseById(long id) {
        StudentBaseInfo baseInfo = studentBaseInfoRepo.findOne(id);
        if(baseInfo == null)return null;
        return new StudentBaseInfoVo(baseInfo);
    }

    @Override
    public StudentBaseInfoVo getStudentBaseByUserId(long userId) {
        StudentBaseInfo baseInfo = studentBaseInfoRepo.findByBaseInfo_User_Id(userId);
        if(baseInfo == null)return null;
        return new StudentBaseInfoVo(baseInfo);
    }

    @Override
    public StudentBaseInfoVo getStudentBaseBySchoolId(String schoolId) {
        StudentBaseInfo baseInfo = studentBaseInfoRepo.findBySchoolId(schoolId);
        if(baseInfo == null)return null;
        return new StudentBaseInfoVo(baseInfo);
    }

    @Override
    public List<StudentBaseInfoVo> getStudentListBaseByDormitory(long dormitoryId, Integer page, Integer size) {
        List<StudentBaseInfo> poList= studentBaseInfoRepo.findByDormitory_Id(dormitoryId, new PageRequest(page, size, new Sort("id")));
        List<StudentBaseInfoVo> voList = new ArrayList<>();
        for(StudentBaseInfo baseInfo : poList){
            voList.add(new StudentBaseInfoVo(baseInfo));
        }
        return voList;
    }

    @Override
    public List<StudentBaseInfoVo> getStudentListBaseByClass(long classId, Integer page, Integer size) {
        List<StudentBaseInfo> poList= studentBaseInfoRepo.findByAClass_Id(classId, new PageRequest(page, size, new Sort("id")));
        List<StudentBaseInfoVo> voList = new ArrayList<>();
        for(StudentBaseInfo baseInfo : poList){
            voList.add(new StudentBaseInfoVo(baseInfo));
        }
        return voList;
    }

    @Override
    public List<StudentBaseInfoVo> getStudentListBaseByMajor(long majorId, Integer page, Integer size) {
        List<StudentBaseInfo> poList= studentBaseInfoRepo.findByAClass_Major_Id(majorId, new PageRequest(page, size, new Sort("id")));
        List<StudentBaseInfoVo> voList = new ArrayList<>();
        for(StudentBaseInfo baseInfo : poList){
            voList.add(new StudentBaseInfoVo(baseInfo));
        }
        return voList;
    }
}
