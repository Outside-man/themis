package dangod.themis.service;

import dangod.themis.model.vo.score.record.*;

import java.util.List;

public interface StudentRecordService {
    List<ActivityVo> getActivityByUserId(long userId, Integer page, Integer size);

    List<OfficeVo> getOfficeByUserId(long userId, Integer page, Integer size);

    List<HonorVo> getHonorByUserId(long userId, Integer page, Integer size);

    List<PracticeVo> getPracticeByUserId(long userId, Integer page, Integer size);

    List<ReserveVo> getReserveByUserId(long userId, Integer page, Integer size);

    List<SkillVo> getSkillByUserId(long userId, Integer page, Integer size);

    List<VolunteerVo> getVolunteerByUserId(long userId, Integer page, Integer size);




}
