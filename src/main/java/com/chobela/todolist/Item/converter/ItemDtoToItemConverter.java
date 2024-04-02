package com.chobela.todolist.Item.converter;

import com.chobela.todolist.Item.Item;
import com.chobela.todolist.Item.dto.ItemDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ItemDtoToItemConverter implements Converter<ItemDto, Item> {

    @Override
    public Item convert(ItemDto itemDto) {
        Item item = new Item();
        item.setId(itemDto.id());
        item.setName(itemDto.name());
        item.setDescription(itemDto.description());
        item.setDone(itemDto.isDone());
        return item;
    }
}
