package dangod.themis.service.impl.club;

import dangod.themis.dao.club.ApplicationRepo;
import dangod.themis.dao.club.ApprovalRepo;
import dangod.themis.dao.common.UserBaseInfoRepo;
import dangod.themis.model.po.club.Application;
import dangod.themis.model.po.club.Approval;
import dangod.themis.model.po.club.ClubRole;
import dangod.themis.model.vo.club.ApprovalVo;
import dangod.themis.service.club.ApplicationService;
import dangod.themis.service.club.ApproveService;
import dangod.themis.service.club.RoleService;
import dangod.themis.service.common.UserInfoService;
import dangod.themis.service.common.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;
@Service
public class ApproveServiceImpl implements ApproveService {
    @Autowired
    private ApprovalRepo approvalRepo;
    @Autowired
    private ApplicationRepo applicationRepo;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserBaseInfoRepo userBaseInfoRepo;
    @Override
    public List<ApprovalVo> getApprovalVoListById(long applicationId) {
        List<ApprovalVo> approvalVoList = new ArrayList<>();
        List<Approval> approvalList = approvalRepo.findByApplication_Id(applicationId, new Sort(Sort.Direction.ASC, "approvalLV"));
        for(Approval a : approvalList){
            approvalVoList.add(new ApprovalVo(a));
        }
        return approvalVoList;
    }
    //审批处理成事务，防止出问题
    @Override
    @Transactional
    public ApprovalVo approve(long applicationId, long userId, Integer result, String comment) {
        ApprovalVo approvalVo;
        try {
            ClubRole role = roleService.getRole(userId);
            if (role.getLv() < 2) throw new Exception("没有权限");
            Application app = applicationRepo.findOne(applicationId);
            if (app.getLv() >= role.getLv() && app.getStatus() == 1) throw new Exception("当前等级已经审批过或者审批流程结束");
            Approval approval = new Approval(role.getLv(), result, comment, userBaseInfoRepo.findByUser_Id(userId), app);
            approvalRepo.save(approval);
            app.setLv(role.getLv());
            if(result == 0) {//0：不同意
                app.setStatus(-1);
                app.setLv(100);
            }
            if(result == 1&&app.getLv() == 4) {//指导老师审批且同意 审批通过
                app.setStatus(0);
            }
            approvalVo = new ApprovalVo(approval);
            applicationRepo.save(app);
            //TODO 邮件发送 考虑邮件error 数据库回滚问题
//            if(app.getStatus() == 0){//审核通过
//
//            }else if(app.getStatus() == -1){//审核不通过
//
//            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return null;
        }
        return approvalVo;
    }
}
