package com.chobela.todolist.Item;

import com.chobela.todolist.Item.dto.ItemDto;
import com.chobela.todolist.system.MyConfigurationProperties;
import com.chobela.todolist.system.StatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

    @Configuration
    static class TestConfig {
        // Define any necessary bean configurations for testing
        @Bean
        public MyConfigurationProperties myConfigurationProperties() {
            MyConfigurationProperties properties = new MyConfigurationProperties();
            properties.setSomeProperty("testValue");
            return properties;
        }
    }

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItemService itemService;

//    @Autowired
//    ObjectMapper objectMapper;

    List<Item> items;

    @BeforeEach
    void setUp() {
        this.items = new ArrayList<>();

        Item item1 = new Item();
        item1.setId("1");
        item1.setName("Pack my bags");
        item1.setDescription("Pack my bags for the trip");
        item1.setDone(false);
        items.add(item1);

        Item item2 = new Item();
        item2.setId("2");
        item2.setName("Buy a ticket");
        item2.setDescription("Buy a ticket for the trip");
        item2.setDone(false);
        items.add(item2);

        Item item3 = new Item();
        item3.setId("3");
        item3.setName("Clean the house");
        item3.setDescription("Clean the house before the trip");
        item3.setDone(false);
        items.add(item3);

        Item item4 = new Item();
        item4.setId("4");
        item4.setName("Get a visa");
        item4.setDescription("Get a visa for the trip");
        item4.setDone(false);
        items.add(item4);

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void findItemByIdSuccess() throws Exception {

        //Given (fake data and define the behaviour of the mock) -> arrange inputs and targets.
        given(itemService.findItemById("1")).willReturn(this.items.get(0));

        //When. (call the method) -> act on the target
        //Then. (assert the result) -> assert the result (compare the actual result with the expected result).
        // make a fake http request
        //for controller test, combine when and then together.
        this.mockMvc.perform(get("/api/v1/items/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Item found"))
                .andExpect(jsonPath("$.data.id").value("1"));


    }

    @Test
    void findItemByIdNotFound() throws Exception {

        //Given (fake data and define the behaviour of the mock) -> arrange inputs and targets.
        given(itemService.findItemById("1")).willThrow(new ItemNotFoundException("Item with id 1 not found"));

        //When. (call the method) -> act on the target
        //Then. (assert the result) -> assert the result (compare the actual result with the expected result).
        // make a fake http request
        //for controller test, combine when and then together.
        this.mockMvc.perform(get("/api/v1/items/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Item with ID 1 not found"))
                .andExpect(jsonPath("$.data").isEmpty());

    }

    @Test
    void findAllItemsSuccess() throws Exception {

        //Given (fake data and define the behaviour of the mock) -> arrange inputs and targets.
        given(itemService.findAllItems()).willReturn(this.items);

        //When. (call the method) -> act on the target
        //Then. (assert the result) -> assert the result (compare the actual result with the expected result).
        // make a fake http request
        //for controller test, combine when and then together.
        this.mockMvc.perform(get("/api/v1/items").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Items found"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(this.items.size())));
    }

//    @Test
//    void addItemSuccess() throws Exception {
//
//        ItemDto itemDto = new ItemDto(null, "Pack my bags",
//                "Pack my bags for the trip", false, null);
//
//       String json = this.objectMapper.writeValueAsString(itemDto);
//
//        Item item = new Item();
//        item.setId("1");
//        item.setName("Pack my bags");
//        item.setDescription("Pack my bags for the trip");
//        item.setDone(false);
//
//
//        //Given (fake data and define the behaviour of the mock) -> arrange inputs and targets.
//        given(itemService.saveItem(Mockito.any(Item.class))).willReturn(item);
//
//        //when and then
//        this.mockMvc.perform(post("/api/v1/items")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.flag").value(true))
//                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
//                .andExpect(jsonPath("$.message").value("Items Added"))
//                .andExpect(jsonPath("$.data.id").isNotEmpty())
//                .andExpect(jsonPath("$.data.name").value(item.getName()))
//                .andExpect(jsonPath("$.data.description").value(item.getDescription()))
//                .andExpect(jsonPath("$.data.done").value(item.isDone()));
//
//
//    }
}