package dangod.themis.service;

import dangod.themis.model.po.User;

public interface UserService {
    User check(String username, String password);

    User update(User user);
}
