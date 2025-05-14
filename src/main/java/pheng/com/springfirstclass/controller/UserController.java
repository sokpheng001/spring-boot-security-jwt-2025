package pheng.com.springfirstclass.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pheng.com.springfirstclass.base.BaseResponse;
import pheng.com.springfirstclass.exception.UserAlreadyExistException;
import pheng.com.springfirstclass.exception.UserNotFoundException;
import pheng.com.springfirstclass.model.dto.CreateUserDto;
import pheng.com.springfirstclass.model.dto.UpdateUserDto;
import pheng.com.springfirstclass.repo.UserRepositoryPaginate;
import pheng.com.springfirstclass.service.UserServiceImpl;

import java.time.Instant;
import java.util.Date;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;
    private final UserRepositoryPaginate userRepositoryPaginate;

    @GetMapping("")
    public BaseResponse<Object> getAllUsers(@RequestParam(defaultValue = "0") int pageNumber,
                                            @RequestParam(defaultValue = "10") int size) {
        return BaseResponse.builder()
                .code(HttpStatus.OK.toString())
                .message("Get All Users")
                .timestamp(Date.from(Instant.now()))
                .data(userService.getAllUsers(pageNumber, size))
                .build();
    }
    @PostMapping()
    public BaseResponse<Object> createUser(@RequestBody CreateUserDto user) throws UserAlreadyExistException {
        return BaseResponse.builder()
                .code(HttpStatus.OK.toString())
                .message("Get All Users")
                .timestamp(Date.from(Instant.now()))
                .data(userService.createUser(user))
                .build();
    }
    @PatchMapping("/{uuid}")
    public BaseResponse<Object> updateUser(@PathVariable("uuid") String uuid, @RequestBody UpdateUserDto updateUserDto) throws UserNotFoundException {
        return BaseResponse.builder()
                .code(HttpStatus.OK.toString())
                .message("Updated user successfully")
                .timestamp(Date.from(Instant.now()))
                .data(userService.updateUser(uuid, updateUserDto))
                .build();
    }
    @DeleteMapping("/{uuid}")
    public BaseResponse<Object> deleteUser(@PathVariable String uuid) {
        return BaseResponse.builder()
                .code(HttpStatus.OK.toString())
                .message("Deleted user successfully")
                .timestamp(Date.from(Instant.now()))
                .data(userService.deleteUser(uuid))
                .build();
    }
}
