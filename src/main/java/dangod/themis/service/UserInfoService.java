package dangod.themis.service;

import dangod.themis.model.po.UserBaseInfo;

public interface UserInfoService {

    UserBaseInfo getBaseInfoByUserId(long userid);

    UserBaseInfo addUserBaseInfo(String realname, String email, String sex);

    UserBaseInfo updateUserBaseInfo(UserBaseInfo baseInfo);
}
