package dangod.themis.dao;

import dangod.themis.model.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    public User findByUsernameAndPassword(String username, String password);
}
