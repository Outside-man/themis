package dangod.themis.service.impl.club;

import dangod.themis.dao.club.ApprovalRepo;
import dangod.themis.model.po.club.Approval;
import dangod.themis.model.vo.club.ApprovalVo;
import dangod.themis.service.club.ApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ApproveServiceImpl implements ApproveService {
    @Autowired
    private ApprovalRepo approvalRepo;
    @Override
    public List<ApprovalVo> getApprovalVo(long applicationId) {
        List<ApprovalVo> approvalVoList = new ArrayList<>();
        List<Approval> approvalList = approvalRepo.findByApplication_Id(applicationId, new Sort(Sort.Direction.DESC, "lv"));
        for(Approval a : approvalList){
            approvalVoList.add(new ApprovalVo(a));
        }
        return approvalVoList;
    }

    @Override
    public ApprovalVo approve(long application, long userId) {
        return null;
    }
}
