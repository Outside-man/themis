package dangod.themis.service;

import dangod.themis.model.po.UserBaseInfo;

public interface UserInfoService {

    UserBaseInfo getBaseInfoByToken(String token);

    UserBaseInfo addBaseInfo(String realname, String email, String sex);
}
