package pheng.com.springfirstclass.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pheng.com.springfirstclass.model.Role;
@Repository

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByUuid(String uuid);
    Role findRoleByName(String roleName);
}
