package dangod.themis.service.impl;

import dangod.themis.dao.score.ClassRepo;
import dangod.themis.dao.score.MajorRepo;
import dangod.themis.model.po.score.Class;
import dangod.themis.model.po.score.Major;
import dangod.themis.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private MajorRepo majorRepo;
    @Autowired
    private ClassRepo classRepo;
    @Override
    public Integer addMajor(String majorName, Integer year) {
        try {
            majorRepo.save(new Major(majorName, year));
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public Integer addClass(long majorId, Integer classNum) {
        Major major = getMajorById(majorId);
        if(major == null)return -1;
        try{
            classRepo.save(new Class(classNum, major));
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    private Major getMajorById(long majorId){
        return majorRepo.findOne(majorId);
    }
}
