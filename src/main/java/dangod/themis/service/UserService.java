package dangod.themis.service;

import dangod.themis.model.po.User;

public interface UserService {
    /**
     * 登陆检查
     * @param username
     * @param password
     * @return
     */
    User check(String username, String password);

    /**
     * 增加账户
     * @param username
     * @param password
     * @return
     */
    User add(String username, String password);

    User update(User user);
}
