package dangod.themis.dao.authority;

import dangod.themis.model.po.authority.AuthorityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityUserRepo extends JpaRepository<AuthorityUser, Long> {
    @Query(value = "select * from core_authority_user where user_id = :userId", nativeQuery=true)
    AuthorityUser findByUserId(@Param("userId")Long userId);
}
