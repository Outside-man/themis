package dangod.themis.service;

import dangod.themis.model.po.UserBaseInfo;
import dangod.themis.model.vo.UserBaseInfoVo;

public interface UserInfoService {

    UserBaseInfoVo getBaseInfoByUserId(long userid);

    Integer addUserBaseInfo(String realname, String email, String sex);

    UserBaseInfoVo updateUserBaseInfo(UserBaseInfo baseInfo);
}
