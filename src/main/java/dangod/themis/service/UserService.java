package dangod.themis.service;

import dangod.themis.model.po.User;

public interface UserService {
    /**
     * 登陆检查
     * @param username
     * @param password
     * @return
     */
    Long check(String username, String password);

    /**
     * 增加账户
     * @param username
     * @param password
     * @return 0:success
     */
    Integer add(String username, String password);

    /**
     * 用户修改密码
     * @param userId
     * @param password
     * @return 0:success
     */
    Integer updatePassword(Long userId, String password);

    User getUserById(Long userId);
}
