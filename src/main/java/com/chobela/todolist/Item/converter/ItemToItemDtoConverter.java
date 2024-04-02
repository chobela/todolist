package com.chobela.todolist.Item.converter;


import com.chobela.todolist.Item.Item;
import com.chobela.todolist.Item.dto.ItemDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ItemToItemDtoConverter implements Converter<Item, ItemDto> {



    @Override
    public ItemDto convert(Item item) {
        ItemDto itemDto = new ItemDto(item.getId(), item.getName(), item.getDescription(), item.isDone());

        return itemDto;
    }
}
