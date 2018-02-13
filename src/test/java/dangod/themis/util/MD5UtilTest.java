package dangod.themis.util;

import com.alibaba.fastjson.JSON;
import dangod.themis.dao.authority.AuthorityUserRepo;
import dangod.themis.model.po.authority.AuthorityUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.annotations.Cacheable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class MD5UtilTest {
    @Autowired
    private AuthorityUserRepo authorityUserRepo;
    @Test
    public void MD5() throws Exception {
        System.out.println(MD5Util.MD5("1"));

    }


}