package com.chobela.todolist.Item;

public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(String id){
        super("Item with id " + id + " not found");
    }

}
