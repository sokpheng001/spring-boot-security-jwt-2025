package pheng.com.springfirstclass.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pheng.com.springfirstclass.exception.RoleException;
import pheng.com.springfirstclass.model.Role;
import pheng.com.springfirstclass.model.dto.CreateRoleDto;
import pheng.com.springfirstclass.service.RoleServiceImpl;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleServiceImpl roleService;
    @PostMapping("")
    public Role createRole(@RequestBody CreateRoleDto createRoleDto) throws RoleException {
        return roleService.createNewRole(createRoleDto);
    }
}
