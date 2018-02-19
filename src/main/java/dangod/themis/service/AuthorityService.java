package dangod.themis.service;

import dangod.themis.model.vo.MenuVo;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface AuthorityService {

    Integer initAuthorityTable();
    /**
     * 通过userId 获取用户的菜单
     * @param userId
     * @return
     */

    List<MenuVo> getMenuByUserId(long userId);

    List<Long> getAuthoritiesByUserId(long userId);
}
