package dangod.themis.service.impl.score;

import dangod.themis.core.util.BaseFile;
import dangod.themis.core.util.HSSF;
import dangod.themis.dao.score.ClassRepo;
import dangod.themis.dao.score.DormitoryRepo;
import dangod.themis.dao.score.StudentBaseInfoRepo;
import dangod.themis.model.po.common.Inform;
import dangod.themis.model.po.common.UserBaseInfo;
import dangod.themis.model.po.score.Class;
import dangod.themis.model.po.score.Dormitory;
import dangod.themis.model.po.score.StudentBaseInfo;
import dangod.themis.model.vo.score.StudentBaseInfoVo;
import dangod.themis.service.StudentBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
@Service
public class StudentBaseInfoServiceImpl implements StudentBaseInfoService {
    @Autowired
    private StudentBaseInfoRepo studentBaseInfoRepo;
    @Autowired
    private ClassRepo classRepo;
    @Autowired
    private DormitoryRepo dormitoryRepo;

    private static final String STU_BASE_IMPORT_PATH = BaseFile.FOLDER + "score" + File.separator + "import" + File.separator + "base";

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
    public List<StudentBaseInfoVo> getStudentListBaseAll(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, new Sort("id"));
        Page<StudentBaseInfo> poList= studentBaseInfoRepo.findAll(pageable);
        List<StudentBaseInfoVo> voList = new ArrayList<>();
        for(StudentBaseInfo baseInfo : poList){
            voList.add(new StudentBaseInfoVo(baseInfo));
        }
        return voList;
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

    @Override
    public StudentBaseInfoVo updateBaseInfo(String stuId, long dormitoryId, String political) {
        Dormitory dormitory = null;
        if(dormitoryId != -1) {
            dormitory = dormitoryRepo.findOne(dormitoryId);
            if(dormitory == null)return null;
        }
        StudentBaseInfo baseInfo = studentBaseInfoRepo.findByStuId(stuId);
        if(baseInfo == null)return null;
        baseInfo.setPolitical(political);
        baseInfo.setDormitory(dormitory);
        try {
            studentBaseInfoRepo.saveAndFlush(baseInfo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return new StudentBaseInfoVo(baseInfo);
    }

    @Override
    public Integer addStudentBaseByFile(MultipartFile file, String opName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分ss秒");
        long now = Calendar.getInstance().getTime().getTime();
        String fileName = String.format("[%s](%s)import", sdf.format(now), opName);
        if(BaseFile.upload(file, STU_BASE_IMPORT_PATH, fileName) == 0){
            String[] arr = file.getOriginalFilename().split("[.]");
            String suffix = "." + arr[arr.length - 1];
            HSSF hssf = new HSSF(STU_BASE_IMPORT_PATH, fileName + suffix);
//            hssf.open();
            for(int i = 1;i<5;i++)
                for(int j =0;j<5;j++)
                    System.out.println(hssf.get(0,i,j));
        }else {
            return -1;
        }
        return 0;
    }

}
