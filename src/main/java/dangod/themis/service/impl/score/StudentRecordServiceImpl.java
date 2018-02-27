package dangod.themis.service.impl.score;

import dangod.themis.dao.score.record.*;
import dangod.themis.model.po.score.record.*;
import dangod.themis.model.vo.score.record.*;
import dangod.themis.service.StudentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static dangod.themis.config.ScoreConstant.ACTIVITY_SCORE;

@Service
public class StudentRecordServiceImpl implements StudentRecordService{
    @Autowired
    private ActivityRepo activityRepo;
    @Autowired
    private OfficeRepo officeRepo;
    @Autowired
    private HonorRepo honorRepo;
    @Autowired
    private VolunteerRepo volunteerRepo;
    @Autowired
    private SkillRepo skillRepo;
    @Autowired
    private ReserveRepo reserveRepo;
    @Autowired
    private PracticeRepo practiceRepo;
    @Override
    public List<ActivityVo> getActivityByUserIdAndTerm(long userId, String term, Integer page, Integer size) {
        List<Activity> poList = activityRepo.findByBaseInfo_BaseInfo_User_IdAndTerm(userId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<ActivityVo> voList = new ArrayList<>();
        for(Activity entity : poList){
            voList.add(new ActivityVo(entity));
        }
        return voList;
    }

    @Override
    public List<ActivityVo> getActivityByStuIdAndTerm(String stuId, String term, Integer page, Integer size) {
        List<Activity> poList = activityRepo.findByBaseInfo_StuIdAndTerm(stuId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<ActivityVo> voList = new ArrayList<>();
        for(Activity entity : poList){
            voList.add(new ActivityVo(entity));
        }
        return voList;
    }

    @Override
    public List<HonorVo> getHonorByUserIdAndTerm(long userId, String term, Integer page, Integer size) {
        List<Honor> poList = honorRepo.findByBaseInfo_BaseInfo_User_IdAndTerm(userId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<HonorVo> voList = new ArrayList<>();
        for(Honor entity : poList){
            voList.add(new HonorVo(entity));
        }
        return voList;
    }

    @Override
    public List<HonorVo> getHonorByStuIdAndTerm(String stuId, String term, Integer page, Integer size) {
        List<Honor> poList = honorRepo.findByBaseInfo_StuIdAndTerm(stuId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<HonorVo> voList = new ArrayList<>();
        for(Honor entity : poList){
            voList.add(new HonorVo(entity));
        }
        return voList;
    }

    @Override
    public List<OfficeVo> getOfficeByUserIdAndTerm(long userId, String term, Integer page, Integer size) {
        List<Office> poList = officeRepo.findByBaseInfo_BaseInfo_User_IdAndTerm(userId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<OfficeVo> voList = new ArrayList<>();
        for(Office entity : poList){
            voList.add(new OfficeVo(entity));
        }
        return voList;
    }

    @Override
    public List<OfficeVo> getOfficeByStuIdAndTerm(String stuId, String term, Integer page, Integer size) {
        List<Office> poList = officeRepo.findByBaseInfo_StuIdAndTerm(stuId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<OfficeVo> voList = new ArrayList<>();
        for(Office entity : poList){
            voList.add(new OfficeVo(entity));
        }
        return voList;
    }

    @Override
    public List<PracticeVo> getPracticeByUserIdAndTerm(long userId, String term, Integer page, Integer size) {
        List<Practice> poList = practiceRepo.findByBaseInfo_BaseInfo_User_IdAndTerm(userId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<PracticeVo> voList = new ArrayList<>();
        for(Practice entity : poList){
            voList.add(new PracticeVo(entity));
        }
        return voList;
    }

    @Override
    public List<PracticeVo> getPracticeByStuIdAndTerm(String stuId, String term, Integer page, Integer size) {
        List<Practice> poList = practiceRepo.findByBaseInfo_StuIdAndTerm(stuId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<PracticeVo> voList = new ArrayList<>();
        for(Practice entity : poList){
            voList.add(new PracticeVo(entity));
        }
        return voList;
    }

    @Override
    public List<ReserveVo> getReserveByUserIdAndTerm(long userId, String term, Integer page, Integer size) {
        List<Reserve> poList = reserveRepo.findByBaseInfo_BaseInfo_User_IdAndTerm(userId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<ReserveVo> voList = new ArrayList<>();
        for(Reserve entity : poList){
            voList.add(new ReserveVo(entity));
        }
        return voList;
    }

    @Override
    public List<ReserveVo> getReserveByStuIdAndTerm(String stuId, String term, Integer page, Integer size) {
        List<Reserve> poList = reserveRepo.findByBaseInfo_StuIdAndTerm(stuId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<ReserveVo> voList = new ArrayList<>();
        for(Reserve entity : poList){
            voList.add(new ReserveVo(entity));
        }
        return voList;
    }

    @Override
    public List<SkillVo> getSkillByUserIdAndTerm(long userId, String term, Integer page, Integer size) {
        List<Skill> poList = skillRepo.findByBaseInfo_BaseInfo_User_IdAndTerm(userId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<SkillVo> voList = new ArrayList<>();
        for(Skill entity : poList){
            voList.add(new SkillVo(entity));
        }
        return voList;
    }

    @Override
    public List<SkillVo> getSkillByStuIdAndTerm(String stuId, String term, Integer page, Integer size) {
        List<Skill> poList = skillRepo.findByBaseInfo_StuIdAndTerm(stuId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<SkillVo> voList = new ArrayList<>();
        for(Skill entity : poList){
            voList.add(new SkillVo(entity));
        }
        return voList;
    }

    @Override
    public List<VolunteerVo> getVolunteerByUserIdAndTerm(long userId, String term, Integer page, Integer size) {
        List<Volunteer> poList = volunteerRepo.findByBaseInfo_BaseInfo_User_IdAndTerm(userId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<VolunteerVo> voList = new ArrayList<>();
        for(Volunteer entity : poList){
            voList.add(new VolunteerVo(entity));
        }
        return voList;
    }

    @Override
    public List<VolunteerVo> getVolunteerByStuIdAndTerm(String stuId, String term, Integer page, Integer size) {
        List<Volunteer> poList = volunteerRepo.findByBaseInfo_StuIdAndTerm(stuId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<VolunteerVo> voList = new ArrayList<>();
        for(Volunteer entity : poList){
            voList.add(new VolunteerVo(entity));
        }
        return voList;
    }

}
