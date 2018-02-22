package dangod.themis.service.impl.score;

import dangod.themis.dao.score.record.ActivityRepo;
import dangod.themis.dao.score.record.OfficeRepo;
import dangod.themis.model.po.score.record.Activity;
import dangod.themis.model.po.score.record.Office;
import dangod.themis.model.vo.score.record.ActivityVo;
import dangod.themis.model.vo.score.record.OfficeVo;
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
    @Override
    public List<ActivityVo> getActivityByUserId(long userId, Integer page, Integer size) {
        List<Activity> poList = activityRepo.findByBaseInfo_BaseInfo_User_Id(userId, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<ActivityVo> voList = new ArrayList<>();
        for(Activity activity : poList){
            voList.add(new ActivityVo(activity, ACTIVITY_SCORE));
        }
        return voList;
    }

    @Override
    public List<OfficeVo> getOfficeByUserId(long userId, Integer page, Integer size) {
        List<Office> poList = officeRepo.findByBaseInfo_BaseInfo_User_Id(userId, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<OfficeVo> voList = new ArrayList<>();
        for(Office office : poList){
            voList.add(new OfficeVo(office));
        }
        return voList;
    }
}
