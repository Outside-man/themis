package dangod.themis.service.club;

import dangod.themis.model.vo.club.ApprovalVo;

import java.util.List;

public interface ApproveService {
    List<ApprovalVo> getApprovalVo(long applicationId);

    ApprovalVo approve(long application, long userId);//用role 确定审批等级
}
