package com.chobela.todolist.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//optional annotation
public interface ItemRepository extends JpaRepository<Item, String> {

}
