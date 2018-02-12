package dangod.themis.dao;

import dangod.themis.model.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Integer countByUsername(String username);
}
