package dangod.themis.service;

import dangod.themis.model.vo.score.record.ActivityVo;
import dangod.themis.model.vo.score.record.OfficeVo;

import java.util.List;

public interface StudentRecordService {
    List<ActivityVo> getActivityByUserId(long userId, Integer page, Integer size);

    List<OfficeVo> getOfficeByUserId(long userId, Integer page, Integer size);
}
