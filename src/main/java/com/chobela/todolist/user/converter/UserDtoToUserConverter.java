package com.chobela.todolist.user.converter;

import com.chobela.todolist.user.TodoUser;
import com.chobela.todolist.user.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, TodoUser> {

    @Override
    public TodoUser convert(UserDto userDto) {
        TodoUser todoUser = new TodoUser();
        todoUser.setUsername(userDto.username());
        todoUser.setRoles(userDto.roles());
        return todoUser;
    }
}
