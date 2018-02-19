package dangod.themis.controller.base;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    protected static long getUserId(HttpServletRequest request){
        return Long.parseLong((String)request.getAttribute("userId"));
    }
}
