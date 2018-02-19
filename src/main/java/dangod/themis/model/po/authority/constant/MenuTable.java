package dangod.themis.model.po.authority.constant;

import dangod.themis.model.po.authority.AuthorityMenu;

import java.util.HashMap;
import java.util.Map;

public class MenuTable {
    public static final Map<Long, AuthorityMenu> MENU_TABLE = new HashMap();
    static{
        MENU_TABLE.put(1L, new AuthorityMenu(1L, "self", "cogs", "个人资料", "/self", null));
        MENU_TABLE.put(2L, new AuthorityMenu(2L, "inform", "file", "公告", "/inform", null));
        MENU_TABLE.put(3L, new AuthorityMenu(3L, "inform/send", "send", "公告发送", "send", MENU_TABLE.get(2L)));
        MENU_TABLE.put(4L, new AuthorityMenu(4L, "inform/mine", "envelope-open-o", "我的公告", "mine", MENU_TABLE.get(2L)));
        MENU_TABLE.put(5L, new AuthorityMenu(5L, "inform/manage", "file-text-o", "公告管理", "menage", MENU_TABLE.get(2L)));
        MENU_TABLE.put(6L, new AuthorityMenu(6L, "user", "cogs", "用户管理", "/user", null));
        MENU_TABLE.put(7L, new AuthorityMenu(7L, "user/account", "cogs", "账号管理", "/account", MENU_TABLE.get(6L)));
    }

}
