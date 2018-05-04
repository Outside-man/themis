package dangod.themis.service.impl.score;

import dangod.themis.dao.score.record.*;
import dangod.themis.model.po.score.record.*;
import dangod.themis.model.vo.score.record.*;
import dangod.themis.service.score.StudentRecordService;
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
<#list entityList as entity>
    @Override
    public List<${entity}Vo> get${entity}ByUserIdAndTerm(long userId, String term, Integer page, Integer size) {
        List<${entity}> poList = ${entity?uncap_first}Repo.findByBaseInfo_BaseInfo_User_IdAndTerm(userId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<${entity}Vo> voList = new ArrayList<>();
        for(${entity} entity : poList){
            voList.add(new ${entity}Vo(entity));
        }
        return voList;
    }

    @Override
    public List<${entity}Vo> get${entity}ByStuIdAndTerm(String stuId, String term, Integer page, Integer size) {
        List<${entity}> poList = ${entity?uncap_first}Repo.findByBaseInfo_StuIdAndTerm(stuId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<${entity}Vo> voList = new ArrayList<>();
        for(${entity} entity : poList){
            voList.add(new ${entity}Vo(entity));
        }
        return voList;
    }

</#list>
}
