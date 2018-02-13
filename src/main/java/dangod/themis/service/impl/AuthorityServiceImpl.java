package dangod.themis.service.impl;

import dangod.themis.dao.authority.AuthorityMenuRepo;
import dangod.themis.dao.authority.AuthorityTypeRepo;
import dangod.themis.dao.authority.AuthorityUserRepo;
import dangod.themis.model.po.authority.AuthorityMenu;
import dangod.themis.model.po.authority.AuthorityType;
import dangod.themis.model.po.authority.AuthorityUser;
import dangod.themis.model.vo.MenuVo;
import dangod.themis.model.vo.TokenVo;
import dangod.themis.service.AuthorityService;
import dangod.themis.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthorityUserRepo userRepo;
    @Autowired
    private AuthorityTypeRepo typeRepo;
    @Autowired
    private AuthorityMenuRepo menuRepo;

    @Override
    public List<MenuVo> getMenuByToken(String token) {
        if(!tokenService.checkToken(token))
            return null;
        TokenVo tokenVo;
        try {
            tokenVo = new TokenVo(token);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Token格式错误");
            return null;
        }

        //获取用户-权限
        AuthorityUser user  = userRepo.findByUserId(tokenVo.getUserId());
        List<Long> list = user.getList();

        //获取权限列表
        List<AuthorityType> authorityList = typeRepo.findAllByIdIn(list);

        //转化菜单
        Map<Long, AuthorityMenu> menuMap = getMenuListByAuthorityList(authorityList);

        //构建菜单结构

        Map<Long, MenuVo> menuVoMap = new HashMap<>();
        for(AuthorityMenu menu : menuMap.values()){
            if(menu.getParent() == null ){
                menuVoMap.put(menu.getId(), new MenuVo(menu));
            }
        }
        for(AuthorityMenu menu : menuMap.values()){
            if(menu.getParent() != null ){
                menuVoMap.get(menu.getParent().getId()).setChildren(new MenuVo(menu));
            }
        }
        List<MenuVo> menuVoList = new ArrayList<>();
        menuVoList.addAll(menuVoMap.values());

        return menuVoList;
    }


    private Map<Long, AuthorityMenu> getMenuListByAuthorityList(List<AuthorityType> authorityList) {
        List<AuthorityMenu> authorityMenus = new ArrayList<>();
        for(AuthorityType type : authorityList)authorityMenus.add(type.getMenu());

        // 将构建菜单的所有母菜单子菜单取出放入map
        Map<Long, AuthorityMenu> menuMap = new HashMap<>();
        boolean allExist;
        while(authorityMenus != null){
            allExist = true;
            for(AuthorityMenu menu : authorityMenus){
                if(!menuMap.containsKey(menu.getId())) {
                    menuMap.put(menu.getId(), menu);
                    allExist = false;
                }
            }
            if(allExist)
                break;
            authorityMenus = findParentMenuList(authorityMenus);
        }
        return menuMap;
    }

    private List<AuthorityMenu> findParentMenuList(List<AuthorityMenu> authorityMenus){
        List<Long> menuList = new ArrayList<>();
        for(AuthorityMenu menu : authorityMenus){
            if(menu.getParent() != null)
                menuList.add(menu.getParent().getId());
        }
        return menuRepo.findAllByIdIn(menuList);
    }
}
