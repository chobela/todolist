package com.chobela.todolist.Item.dto;


import jakarta.validation.constraints.NotEmpty;

public record ItemDto(String id, @NotEmpty String name, String description, boolean isDone) {
}
