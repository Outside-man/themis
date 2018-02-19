package dangod.themis.model.po.authority.constant;

import dangod.themis.model.po.authority.AuthorityType;

import java.util.HashMap;
import java.util.Map;

import static dangod.themis.model.po.authority.constant.MenuTable.MENU_TABLE;

public class TypeTable {
    public static final Map<Long, AuthorityType> TYPE_TABLE = new HashMap();
    static{
        TYPE_TABLE.put(1L, new AuthorityType(1L, "个人管理", null, MENU_TABLE.get(1L)));
        TYPE_TABLE.put(2L, new AuthorityType(2L, "发送公告", null, MENU_TABLE.get(3L)));
        TYPE_TABLE.put(3L, new AuthorityType(3L, "我的公告", null, MENU_TABLE.get(4L)));
        TYPE_TABLE.put(4L, new AuthorityType(4L, "我的公告", null, MENU_TABLE.get(5L)));
        TYPE_TABLE.put(5L, new AuthorityType(5L, "用户管理", null, MENU_TABLE.get(7L)));

    }
}
