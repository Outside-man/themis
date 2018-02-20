package dangod.themis.service;

import dangod.themis.model.vo.UserBaseInfoVo;

public interface UserInfoService {

    //基础信息处理方法

    /**
     * 获取基础信息方法
     * @param userId
     * @return
     */
    UserBaseInfoVo getBaseInfoByUserId(long userId);

    /**
     * 修改基础信息
     * @param userId
     * @param realName
     * @param email
     * @param sex
     * @return
     */
    UserBaseInfoVo updateUserBaseInfo(long userId, String realName, String email, String sex);
}
