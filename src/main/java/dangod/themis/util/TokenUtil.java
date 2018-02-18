package dangod.themis.util;

import dangod.themis.model.vo.TokenVo;
import dangod.themis.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class TokenUtil {
    private static TokenService tokenService;

    @Autowired
    public void setTokenService(TokenService tokenService) {
        TokenUtil.tokenService = tokenService;
    }

    public static long getUserId(String token){
        try{
            if(!tokenService.checkToken(token))
                throw new Exception("token is invalid");
            byte[] bytes = Base64.getDecoder().decode(token);
            String[] arr = new String(bytes,"utf-8").split("-");
            return Long.parseLong(arr[0]);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
