package dangod.themis.service.impl;

import dangod.themis.model.vo.Token;
import dangod.themis.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class RedisTokenServiceIpml implements TokenService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String createToken(long userId) {
        String uuid = UUID.randomUUID().toString().replace("-","");
        Token token = new Token(userId, uuid);

        ValueOperations<String, String> operations=redisTemplate.opsForValue();
        operations.set(String.valueOf(userId), uuid, 30L, TimeUnit.MINUTES);
        return token.toString();
    }

    @Override
    public boolean checkToken(String authentication) {
        try {
            Token token = new Token(authentication);
            String uuid = (String)redisTemplate.boundValueOps(String.valueOf(token.getUserId())).get();
            if (uuid == null || !token.getToken().equals(uuid))
                return false;
            else
                redisTemplate.boundValueOps(String.valueOf(token.getUserId())).expire(30L, TimeUnit.MINUTES);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void deleteToken(long userId) {
        redisTemplate.delete(String.valueOf(userId));
    }

    @Override
    public void deleteToken(String authentication) {
        try {
            Token token = new Token(authentication);
            deleteToken(token.getUserId());
        }catch (Exception e){
            return ;
        }
    }
}
