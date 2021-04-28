package org.personal.springbootbase.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
public class UpsertUserRequestDto {

    @JsonIgnore
    private UUID userId;

    @NotBlank(message = "Name must be filled!")
    private String name;

    @NotBlank(message = "Email must be filled!")
    @Email
    private String email;

    @NotBlank(message = "Password must be filled!")
    private String password;
}
