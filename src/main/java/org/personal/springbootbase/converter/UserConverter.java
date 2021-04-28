package org.personal.springbootbase.converter;

import org.personal.springbootbase.dto.UserDto;
import org.personal.springbootbase.dto.request.UpsertUserRequestDto;
import org.personal.springbootbase.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends BaseDtoConverter<User, UserDto> {

    @Override
    public UserDto toDto(User entity) {
        final UserDto dto = new UserDto();
        BeanUtils.copyProperties(entity, dto);

        return dto;
    }

    public User toEntity(UpsertUserRequestDto requestDto, User user) {
        BeanUtils.copyProperties(requestDto, user);

        return user;
    }

    public User toEntity(UpsertUserRequestDto requestDto) {
        return this.toEntity(requestDto, new User());
    }
}
