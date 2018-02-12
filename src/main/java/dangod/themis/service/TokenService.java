package dangod.themis.service;


public interface TokenService {
    /**
     * 生成用户的token
     * @param userId
     * @return
     */
    String createToken(long userId);

    /**
     * 检查token是否合法
     * @param authentication
     * @return
     */
    boolean checkToken(String authentication);

    /**
     * 删除token
     * @param userId
     */
    void deleteToken(long userId);

    /**
     * 删除token
     * @param authentication
     */
    void deleteToken(String authentication);
}
