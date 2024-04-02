package com.chobela.todolist.user.converter;

import com.chobela.todolist.user.TodoUser;
import com.chobela.todolist.user.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<TodoUser, UserDto> {

    @Override
    public UserDto convert(TodoUser todoUser) {
        return new UserDto(todoUser.getId(), todoUser.getUsername(), todoUser.getRoles());
    }
}
