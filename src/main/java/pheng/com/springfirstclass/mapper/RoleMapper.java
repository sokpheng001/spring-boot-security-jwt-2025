package pheng.com.springfirstclass.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pheng.com.springfirstclass.model.Role;
import pheng.com.springfirstclass.model.dto.CreateRoleDto;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    Role roleToRole(CreateRoleDto role);
}
