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
public class StudentRecordServiceImpl implements StudentRecordService {
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
    public List<ActivityVo> getActivityByUserId(long userId, Integer page, Integer size) {
        List<Activity> poList = activityRepo.findByBaseInfo_BaseInfo_User_IdOrderByTerm(userId, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<ActivityVo> voList = new ArrayList<>();
        for(Activity activity : poList){
            voList.add(new ActivityVo(activity, ACTIVITY_SCORE));
        }
        return voList;
    }

    @Override
    public List<OfficeVo> getOfficeByUserId(long userId, Integer page, Integer size) {
        List<Office> poList = officeRepo.findByBaseInfo_BaseInfo_User_IdOrderByTerm(userId, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<OfficeVo> voList = new ArrayList<>();
        for(Office office : poList){
            voList.add(new OfficeVo(office));
        }
        return voList;
    }

    @Override
    public List<HonorVo> getHonorByUserId(long userId, Integer page, Integer size) {
        List<Honor> poList = honorRepo.findByBaseInfo_BaseInfo_User_IdOrderByTerm(userId, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<HonorVo> voList = new ArrayList<>();
        for(Honor honor : poList){
            voList.add(new HonorVo(honor));
        }
        return voList;
    }

    @Override
    public List<PracticeVo> getPracticeByUserId(long userId, Integer page, Integer size) {
        List<Practice> poList = practiceRepo.findByBaseInfo_BaseInfo_User_IdOrderByTerm(userId, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<PracticeVo> voList = new ArrayList<>();
        for(Practice practice : poList){
            voList.add(new PracticeVo(practice));
        }
        return voList;
    }

    @Override
    public List<ReserveVo> getReserveByUserId(long userId, Integer page, Integer size) {
        List<Reserve> poList = reserveRepo.findByBaseInfo_BaseInfo_User_IdOrderByTerm(userId, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<ReserveVo> voList = new ArrayList<>();
        for(Reserve reserve : poList){
            voList.add(new ReserveVo(reserve));
        }
        return voList;
    }

    @Override
    public List<SkillVo> getSkillByUserId(long userId, Integer page, Integer size) {
        List<Skill> poList = skillRepo.findByBaseInfo_BaseInfo_User_IdOrderByTerm(userId, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<SkillVo> voList = new ArrayList<>();
        for(Skill skill : poList){
            voList.add(new SkillVo(skill));
        }
        return voList;
    }

    @Override
    public List<VolunteerVo> getVolunteerByUserId(long userId, Integer page, Integer size) {
        List<Volunteer> poList = volunteerRepo.findByBaseInfo_BaseInfo_User_IdOrderByTerm(userId, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<VolunteerVo> voList = new ArrayList<>();
        for(Volunteer volunteer : poList){
            voList.add(new VolunteerVo(volunteer));
        }
        return voList;
    }
}
