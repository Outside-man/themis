package dangod.themis.dao;

import dangod.themis.model.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Integer countByUsername(String username);
    @Modifying
    @Query(value = "update User set password = :password where id =:id", nativeQuery = true)
    void updatePasswordById(@Param("id")long id, @Param("password")String password);
}
