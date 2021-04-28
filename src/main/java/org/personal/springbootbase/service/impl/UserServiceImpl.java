package org.personal.springbootbase.service.impl;

import org.personal.springbootbase.converter.UserConverter;
import org.personal.springbootbase.dto.UserDto;
import org.personal.springbootbase.dto.request.UpsertUserRequestDto;
import org.personal.springbootbase.dto.response.FindAllUserResponseDto;
import org.personal.springbootbase.entity.User;
import org.personal.springbootbase.exception.ClientException;
import org.personal.springbootbase.repository.UserRepository;
import org.personal.springbootbase.service.UserService;
import org.personal.springbootbase.type.ErrorType;
import org.personal.springbootbase.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DateTimeUtil dateTimeUtil;

    @Override
    public FindAllUserResponseDto findAll() {
        final List<User> users = userRepository.findByDeletedAtIsNull();

        final FindAllUserResponseDto responseDto = new FindAllUserResponseDto();
        responseDto.setUsers(userConverter.toDtoList(users));

        return responseDto;
    }

    @Override
    public UserDto findById(UUID id) {
        final User user = this.findByUserId(id);

        return userConverter.toDto(user);
    }

    @Override
    public void create(UpsertUserRequestDto requestDto) {
        final User user = userConverter.toEntity(requestDto);

        userRepository.save(user);
    }

    @Override
    public void update(UpsertUserRequestDto requestDto) {
        final User user = this.findByUserId(requestDto.getUserId());

        userRepository.save(userConverter.toEntity(requestDto,user));
    }

    @Override
    public void delete(UUID id) {
        final User user = this.findByUserId(id);
        user.setDeletedAt(dateTimeUtil.now());

        userRepository.save(user);
    }

    private User findByUserId(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new ClientException(ErrorType.USER_NOT_FOUND, "user_not_found_id",id));
    }
}
