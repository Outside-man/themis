package dangod.themis.controller.club;

import dangod.themis.controller.base.BaseController;
import dangod.themis.service.club.ApproveService;
import dangod.themis.service.core.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin
@RestController
@RequestMapping(value = "/club/self")
public class ClubFormController extends BaseController{
    @Autowired
    private ApproveService approveService;
    @RequestMapping(method = GET)
//    @ApiOperation(value = "获取已提交信息")
//    @Authorization
//    @ContainAuthority(CLUB_SELF)
    public String getInformBySelf(HttpServletRequest request, HttpServletResponse response,
//                                  @RequestHeader(AUTHORIZATION)String token,
                                  long applicationId){
//                                  @RequestParam("page")Integer page){
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        approveService.getApprovalVo(applicationId);
        return null;
    }
}
