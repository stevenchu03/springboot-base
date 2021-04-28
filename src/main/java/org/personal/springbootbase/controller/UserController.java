package org.personal.springbootbase.controller;

import org.personal.springbootbase.constant.ApiConstant;
import org.personal.springbootbase.dto.UserDto;
import org.personal.springbootbase.dto.request.UpsertUserRequestDto;
import org.personal.springbootbase.dto.response.FindAllUserResponseDto;
import org.personal.springbootbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(ApiConstant.FIND_ALL_USERS_V1)
    public FindAllUserResponseDto findAll() {
        return userService.findAll();
    }

    @GetMapping(ApiConstant.FIND_USER_BY_ID_V1)
    public UserDto findById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @PostMapping(ApiConstant.CREATE_USER_V1)
    public void create(@RequestBody UpsertUserRequestDto requestDto) {
        userService.create(requestDto);
    }

    @PutMapping(ApiConstant.UPDATE_USER_V1)
    public void update(
            @PathVariable UUID id,
            @RequestBody UpsertUserRequestDto requestDto
    ) {
        requestDto.setUserId(id);

        userService.update(requestDto);
    }

    @DeleteMapping(ApiConstant.DELETE_USER_V1)
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
    }
}
