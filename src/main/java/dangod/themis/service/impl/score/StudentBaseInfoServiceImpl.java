package dangod.themis.service.impl.score;

import dangod.themis.dao.score.ClassRepo;
import dangod.themis.dao.score.DormitoryRepo;
import dangod.themis.dao.score.StudentBaseInfoRepo;
import dangod.themis.model.po.common.UserBaseInfo;
import dangod.themis.model.po.score.Class;
import dangod.themis.model.po.score.Dormitory;
import dangod.themis.model.po.score.StudentBaseInfo;
import dangod.themis.model.vo.StudentBaseInfoVo;
import dangod.themis.service.StudentBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class StudentBaseInfoServiceImpl implements StudentBaseInfoService {
    @Autowired
    private StudentBaseInfoRepo studentBaseInfoRepo;
    @Autowired
    private ClassRepo classRepo;
    @Autowired
    private DormitoryRepo dormitoryRepo;

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
    public StudentBaseInfoVo getStudentBaseByStuId(String stuId) {
        StudentBaseInfo baseInfo = studentBaseInfoRepo.findByStuId(stuId);
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

    @Override
    public boolean checkStuClass(String stuId, long classId) {
        try {
            if(studentBaseInfoRepo.findByStuId(stuId).getaClass().getId() == classId)
                return true;
            else
                return false;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean checkStuMajor(String stuId, long majorId) {
        try {
            if(studentBaseInfoRepo.findByStuId(stuId).getaClass().getMajor().getId() == majorId)
                return true;
            else
                return false;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean checkClassMajor(long classId, long majorId) {
        try {
            if(classRepo.findOne(classId).getMajor().getId() == majorId)
                return true;
            else
                return false;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public StudentBaseInfoVo updateBaseInfo(String stuId, String realName, String sex, long classId, String photo, String entranceTime, long dormitoryId, String political) {
        Dormitory dormitory = null;
        Class aClass = null;
        if(dormitoryId != -1) {
            dormitory = dormitoryRepo.findOne(dormitoryId);
            if(dormitory == null)return null;
        }
        if(classId != -1){
            aClass = classRepo.findOne(classId);
            if(aClass == null)return null;
        }
        StudentBaseInfo baseInfo = studentBaseInfoRepo.findByStuId(stuId);
        if(baseInfo == null)return null;
        baseInfo.setaClass(aClass);
        baseInfo.setDormitory(dormitory);
        baseInfo.setEntrance_time(entranceTime);
        baseInfo.setPhoto(photo);
        baseInfo.setPolitical(political);
        UserBaseInfo base = baseInfo.getBaseInfo();
        base.setRealName(realName);
        base.setSex(sex);
        try {
            studentBaseInfoRepo.saveAndFlush(baseInfo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return new StudentBaseInfoVo(baseInfo);
    }

    @Override
    public StudentBaseInfoVo updateBaseInfo(String stuId, String political) {
        StudentBaseInfo baseInfo = studentBaseInfoRepo.findByStuId(stuId);
        if(baseInfo == null)return null;
        baseInfo.setPolitical(political);
        try {
            studentBaseInfoRepo.saveAndFlush(baseInfo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return new StudentBaseInfoVo(baseInfo);
    }
}
