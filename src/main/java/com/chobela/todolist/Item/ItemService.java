package com.chobela.todolist.Item;

import com.chobela.todolist.Item.utils.IdWorker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    private final IdWorker idWorker;

    public ItemService(ItemRepository itemRepository, IdWorker idWorker){
        this.itemRepository = itemRepository;
        this.idWorker = idWorker;
    }

    public Item findItemById(String itemId){
        return this.itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException(itemId));
    }

    public List<Item> findAllItems(){
        return this.itemRepository.findAll();
    }

    public Item saveItem(Item item){

        item.setId(String.valueOf(this.idWorker.nextId()));
        return this.itemRepository.save(item);
    }

    public Item updateItem(String itemId, Item item){
        Item foundItem = this.findItemById(itemId);
        foundItem.setName(item.getName());
        foundItem.setDone(item.isDone());
        return this.itemRepository.save(foundItem);
    }

    public void deleteItem(String itemId){
        this.itemRepository.deleteById(itemId);
    }
}
