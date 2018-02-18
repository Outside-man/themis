package dangod.themis.dao;

import dangod.themis.model.po.UserBaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBaseInfoRepo extends JpaRepository<UserBaseInfo, Long> {
    UserBaseInfo findByUserId(long userId);
}
