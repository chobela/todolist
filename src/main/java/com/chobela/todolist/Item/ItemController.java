package com.chobela.todolist.Item;

import com.chobela.todolist.Item.converter.ItemDtoToItemConverter;
import com.chobela.todolist.Item.converter.ItemToItemDtoConverter;
import com.chobela.todolist.Item.dto.ItemDto;
import com.chobela.todolist.system.Result;
import com.chobela.todolist.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;

    private final ItemToItemDtoConverter itemToItemDtoConverter;

    private final ItemDtoToItemConverter itemDtoToItemConverter;


    public ItemController(ItemService itemService, ItemToItemDtoConverter itemToItemDtoConverter, ItemDtoToItemConverter itemDtoToItemConverter) {
        this.itemService = itemService;
        this.itemToItemDtoConverter = itemToItemDtoConverter;
        this.itemDtoToItemConverter = itemDtoToItemConverter;
    }

    @GetMapping("/{itemId}")
    public Result findItemById(@PathVariable String itemId) {//Result defines the schema of a response
        Item foundItem = this.itemService.findItemById(itemId);
        ItemDto itemDto = this.itemToItemDtoConverter.convert(foundItem);
        return new Result(true, StatusCode.SUCCESS, "Item Found", itemDto);

    }

    @GetMapping
    public Result findAllItems() {
        List<Item> foundItems = this.itemService.findAllItems();
        //Convert the list of founditems to a list of itemDto
       List <ItemDto> itemDtos =  foundItems.stream().map(this.itemToItemDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Items Found", itemDtos);
    }

    @PostMapping
    public Result addItem(@Valid @RequestBody ItemDto itemDto) {

        Item item = this.itemDtoToItemConverter.convert(itemDto);
        Item savedItem = this.itemService.saveItem(item);

        ItemDto savedItemDto = this.itemToItemDtoConverter.convert(savedItem);
        return new Result(true, StatusCode.SUCCESS, "Item Added", savedItemDto);

    }

    @PatchMapping("/{itemId}")
    public Result updateItem(@PathVariable String itemId, @Valid @RequestBody ItemDto itemDto) {

        Item item = this.itemDtoToItemConverter.convert(itemDto);
        Item updatedItem = this.itemService.updateItem(itemId, item);

        ItemDto updatedItemDto = this.itemToItemDtoConverter.convert(updatedItem);

        return new Result(true, StatusCode.SUCCESS, "Item Updated", updatedItemDto);
    }

    @DeleteMapping("/{itemId}")
    public Result deleteItem(@PathVariable String itemId) {
        this.itemService.deleteItem(itemId);
        return new Result(true, StatusCode.SUCCESS, "Item Deleted", null);
    }

}
