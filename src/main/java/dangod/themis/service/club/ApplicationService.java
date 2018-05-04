package dangod.themis.service.club;

import dangod.themis.model.vo.club.ApplicationVo;
import dangod.themis.model.vo.club.StatusVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ApplicationService {

    ApplicationVo apply(long userId, String activityName, String activityPlace, String activityStart, String activityEnd,
                        String activitypeople, Integer isFine, String introduce, MultipartFile file);

    /**
     * 查看申请表(按照需求，未做身份验证，直接可以访问)
     * @param applicationId
     * @return
     */
    ApplicationVo getApplicationVo(long applicationId);

    /**
     * 获取自己社团申请情况
     * @param clubId
     * @param page
     * @param size
     * @return
     */
    List<StatusVo> getPageStatus(long clubId, Integer page, Integer size);

    /**
     * 按照status获取自己社团申请情况
     * @param clubId
     * @param status -1:未通过 0:通过 1:审核中
     * @param page
     * @param size
     * @return
     */
    List<StatusVo> getPageStatus(long clubId, Integer status, Integer page, Integer size);

    /**
     * 获取审批等级高于等于自己的申请(自己需要审批和自己审批过的)
     * @param userId
     * @param page
     * @param size
     * @return
     */
    List<StatusVo> getAllPageStatus(long userId, Integer page, Integer size);//获取该等级下可以看到的所有可见申请

    /**
     * 获取审批等级高于等于自己的申请(自己需要审批和自己审批过的)
     * @param userId
     * @param status 指定搜索状态 -1:未通过 0:通过 1:审核中
     * @param page
     * @param size
     * @return
     */
    List<StatusVo> getAllPageStatus(long userId, Integer status, Integer page, Integer size);//获取该等级下可以看到的所有可见申请



}
