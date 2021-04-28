package org.personal.springbootbase.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.personal.springbootbase.dto.UserDto;

import java.util.List;

@Getter
@Setter
public class FindAllUserResponseDto {

    private List<UserDto> users;
}
