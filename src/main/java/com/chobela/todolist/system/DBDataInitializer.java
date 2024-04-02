package com.chobela.todolist.system;

import com.chobela.todolist.Item.Item;
import com.chobela.todolist.Item.ItemRepository;
import com.chobela.todolist.user.TodoUser;
import com.chobela.todolist.user.UserRepository;
import com.chobela.todolist.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private final ItemRepository itemRepository;
    private final UserService userService;

    //constructor
    public DBDataInitializer(ItemRepository itemRepository, UserService userService) {
        this.itemRepository = itemRepository;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        Item item1 = new Item();
        item1.setId("1");
        item1.setName("Work on the project");
        item1.setDescription("Complete the project by the end of the week");
        item1.setDone(false);


        Item item2 = new Item();
        item2.setId("2");
        item2.setName("Buy a Ticket");
        item2.setDescription("Buy a ticket for the trip");
        item2.setDone(false);

        Item item3 = new Item();
        item3.setId("3");
        item3.setName("Pack my bags");
        item3.setDescription("Pack my bags for the trip");
        item3.setDone(false);

         itemRepository.save(item1);
         itemRepository.save(item2);
         itemRepository.save(item3);
            TodoUser user1 = new TodoUser();

            user1.setName("Chobela Kakumbi");
            user1.setUsername("chobela");
            user1.setPassword("123456");
            user1.setRoles("admin user");

            userService.save(user1);

    }
}
