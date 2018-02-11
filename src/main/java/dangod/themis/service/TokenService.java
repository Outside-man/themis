package dangod.themis.service;

import dangod.themis.model.vo.Token;

public interface TokenService {
    /**
     * 生成用户的token
     * @param userId
     * @return
     */
    String createToken(long userId);

    /**
     * 检查token是否合法
     * @param token
     * @return
     */
    boolean checkToken(String token);

    /**
     * 删除token
     * @param userId
     */
    void deleteToken(long userId);

    void deleteToken(String authentication);
}
