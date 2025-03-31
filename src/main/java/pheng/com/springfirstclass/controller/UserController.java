package pheng.com.springfirstclass.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pheng.com.springfirstclass.model.User;
import pheng.com.springfirstclass.model.dto.CreateUserDto;
import pheng.com.springfirstclass.model.dto.UserResponseDto;
import pheng.com.springfirstclass.service.UserServiceImpl;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;
    @GetMapping("")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("")
    public UserResponseDto createUser(@RequestBody CreateUserDto user) {
        return userService.createUser(user);
    }
}
