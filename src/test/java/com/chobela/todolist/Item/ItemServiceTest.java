package com.chobela.todolist.Item;

import com.chobela.todolist.Item.utils.IdWorker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock // Creates a mock object of the ItemRepository class
    ItemRepository itemRepository;

    @Mock
    IdWorker idWorker;

    @InjectMocks // Injects the mock objects into the ItemService class
    ItemService itemService;

    List<Item> items;

    @BeforeEach
    void setUp() {
        Item a = new Item();
        a.setId("1");
        a.setName("Pack my bags");
        a.setDescription("Pack my bags for the trip");
        a.setDone(false);

        Item b = new Item();
        b.setId("2");
        b.setName("Buy a ticket");
        b.setDescription("Buy a ticket for the trip");
        b.setDone(false);

        this.items = new ArrayList<>();
        this.items.add(a);
        this.items.add(b);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findItemByIdSuccess() {
        //3 steps
        //Given (fake data and define the behaviour of the mock) -> arrange inputs and targets.
        Item a = new Item();
        a.setId("1");
        a.setName("Pack my bags");
        a.setDescription("Pack my bags for the trip");
        a.setDone(false);


        given(itemRepository.findById("1")).willReturn(Optional.of(a)); //Defines the behaviour of the mock object. This is what the repository returns


        //When. (call the method) -> act on the target
        Item returnedItem =  itemService.findItemById("1"); // This is what the service returns

        //Then. (assert the result) -> assert the result (compare the actual result with the expected result).
        // if true, the test passes. If false, the test fails.

        assertThat(returnedItem.getId()).isEqualTo(a.getId());
        assertThat(returnedItem.getName()).isEqualTo(a.getName());
        assertThat(returnedItem.getDescription()).isEqualTo(a.getDescription());
        assertThat(returnedItem.isDone()).isEqualTo(a.isDone());
        verify(itemRepository, times(1)).findById("1"); // Verifies that the repository method was called once
    }

    @Test

    void findItemByIdNotFound() {

        given(itemRepository.findById(Mockito.any(String.class))).willReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> {
            Item returnedItem =  itemService.findItemById("1");
        });

        assertThat(thrown)
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessage("Item with id 1 not found");

        verify(itemRepository, times(1)).findById("1");

    }

    @Test
    void findAllItemsSuccess() {
        //Given
        given(itemRepository.findAll()).willReturn(this.items);
        //When
        List<Item> returnedItems = itemService.findAllItems();

        //Then
        assertThat(returnedItems.size()).isEqualTo(this.items.size());
        verify(itemRepository, times(1)).findAll();
    }

    @Test
    void saveItemSuccess() {
        //Given
        Item a = new Item();
        a.setName("Read my book");
        a.setDescription("Read my book for the trip");
        a.setDone(false);

        given(idWorker.nextId()).willReturn(4L);
        given(itemRepository.save(a)).willReturn(a);

        //When
        Item returnedItem = itemService.saveItem(a);

        //Then
        assertThat(returnedItem.getId()).isEqualTo("4");
        assertThat(returnedItem.getName()).isEqualTo(a.getName());
        assertThat(returnedItem.getDescription()).isEqualTo(a.getDescription());
        assertThat(returnedItem.isDone()).isEqualTo(a.isDone());
        verify(itemRepository, times(1)).save(a);
    }

}