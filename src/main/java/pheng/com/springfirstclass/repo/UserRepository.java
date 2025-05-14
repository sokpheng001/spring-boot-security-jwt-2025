package pheng.com.springfirstclass.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pheng.com.springfirstclass.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUuid(String uuid);
    Boolean existsUserByEmail(String email);
    Boolean existsUserByUuid(String uuid);
    User findUserByEmail(String email);

}
