package dangod.themis.service;

import dangod.themis.model.po.common.User;
import dangod.themis.model.po.common.UserBaseInfo;

public interface UserService {
    /**
     * 登陆检查
     * @param username
     * @param password
     * @return
     */
    Long checkUser(String username, String password);

    /**
     * 增加账户
     * @param username
     * @param password
     * @param realName
     * @param email
     * @param sex
     * @return 0:success
     */
    Integer addUser(String username, String password, String realName, String email, String sex);

    /**
     * 用户修改密码
     * @param userId
     * @param password
     * @return 0:success
     */
    Integer updatePassword(Long userId, String password);

    User getUserById(Long userId);

    UserBaseInfo addAndCheckUser(String username, String password, String realName, String email, String sex);

}
