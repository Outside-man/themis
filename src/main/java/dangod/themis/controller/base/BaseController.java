package dangod.themis.controller.base;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    protected static long getUserId(HttpServletRequest request){
        return Long.parseLong(getAttribute(request, "userId"));
    }

    protected static String getAttribute(HttpServletRequest request, String key){
        return (String)request.getAttribute(key);
    }
}
