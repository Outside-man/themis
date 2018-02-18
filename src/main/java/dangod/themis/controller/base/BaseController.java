package dangod.themis.controller.base;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    protected static long getUserId(HttpServletRequest request){
        return (long) request.getAttribute("userId");
    }
}
