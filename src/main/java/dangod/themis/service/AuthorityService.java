package dangod.themis.service;

import dangod.themis.model.vo.MenuVo;

import java.util.List;

public interface AuthorityService {
    /**
     * 通过token 获取用户的菜单
     * @param token
     * @return
     */
    List<MenuVo> getMenuByToken(String token);
}
