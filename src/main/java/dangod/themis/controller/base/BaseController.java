package dangod.themis.controller.base;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    protected static final int DEFAULT_SIZE = 6;

    protected static long getUserId(HttpServletRequest request){
        return (long)request.getAttribute("userId");
    }

    protected static long getManageClass(HttpServletRequest request){
        return (long)request.getAttribute("classId");
    }

    protected static long getManageMajor(HttpServletRequest request){
        return (long)request.getAttribute("majorId");
    }

    protected static String getAttribute(HttpServletRequest request, String key){
        return (String)request.getAttribute(key);
    }
    protected static String getParameter(HttpServletRequest request, String key){
        return request.getParameter(key);
    }
}
