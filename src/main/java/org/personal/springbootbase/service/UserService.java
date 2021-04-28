package org.personal.springbootbase.service;

import org.personal.springbootbase.dto.UserDto;
import org.personal.springbootbase.dto.request.UpsertUserRequestDto;
import org.personal.springbootbase.dto.response.FindAllUserResponseDto;

import java.util.UUID;

public interface UserService {

    FindAllUserResponseDto findAll();

    UserDto findById(UUID id);

    void create(UpsertUserRequestDto requestDto);

    void update(UpsertUserRequestDto requestDto);

    void delete(UUID id);
}
