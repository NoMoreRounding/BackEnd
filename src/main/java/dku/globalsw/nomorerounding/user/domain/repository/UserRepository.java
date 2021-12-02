package dku.globalsw.nomorerounding.user.domain.repository;

import dku.globalsw.nomorerounding.user.domain.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByLoginId(String loginId);

    Optional<User> findUserByLoginIdAndEmail(String loginId, String email);

    Optional<User> findUserByEmail(String email);

    Boolean existsByLoginId(String loginId);

    Boolean existsByEmail(String email);
}
