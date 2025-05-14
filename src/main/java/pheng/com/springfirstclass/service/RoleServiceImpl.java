package pheng.com.springfirstclass.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pheng.com.springfirstclass.exception.RoleException;
import pheng.com.springfirstclass.mapper.RoleMapper;
import pheng.com.springfirstclass.model.Role;
import pheng.com.springfirstclass.model.dto.CreateRoleDto;
import pheng.com.springfirstclass.repo.RoleRepository;

import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper = RoleMapper.INSTANCE;
    public Role createNewRole(CreateRoleDto createRoleDto) throws RoleException {
        Role findRole = roleRepository.findRoleByName(createRoleDto.roleName());
        if(findRole != null) {
            throw new RoleException("Role already exist");
        }
        Role role = new Role(null, UUID.randomUUID().toString(), createRoleDto.roleName().toUpperCase(Locale.ROOT));
        return roleRepository.save(role);
    }
}
