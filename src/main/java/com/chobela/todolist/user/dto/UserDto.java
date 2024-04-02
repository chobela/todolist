package com.chobela.todolist.user.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserDto (Integer id,
                      @NotEmpty(message = "Username is required") String username,
                        @NotEmpty(message = "Roles are required") String roles

                      ){

}
