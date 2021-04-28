package org.personal.springbootbase.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
public class UserDto {

    private UUID id;
    private String name;
    private String email;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
