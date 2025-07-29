package com.user.items.controller;

import com.user.items.model.Item;
import com.user.items.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemRepository repo;

    @Test
    void shouldReturnAllItems() throws Exception {
        repo.save(new Item("TestItem"));
        mockMvc.perform(get("/api/v1/items"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TestItem")));
    }

    @Test
    void shouldReturnFilteredItems() throws Exception {
        repo.save(new Item("Orange"));
        mockMvc.perform(get("/api/v1/items?filter=or"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Orange")));
    }
}
