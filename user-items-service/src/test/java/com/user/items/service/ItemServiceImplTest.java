package com.user.items.service;

import com.user.items.model.Item;
import com.user.items.repository.ItemRepository;
import com.user.items.service.impl.ItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceImplTest {

    private ItemRepository repo;
    private ItemServiceImpl service;

    @BeforeEach
    void setup() {
        repo = Mockito.mock(ItemRepository.class);
        service = new ItemServiceImpl(repo);
    }

    @Test
    void shouldReturnAllItemsWhenNoSearchTerm() {
        List<Item> items = Arrays.asList(new Item("Apple"), new Item("Banana"));
        when(repo.findAll()).thenReturn(items);

        List<Item> result = service.getItems(null);
        assertEquals(2, result.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    void shouldFilterItemsBySearchTerm() {
        List<Item> items = Arrays.asList(new Item("Apple"));
        when(repo.findByNameContainingIgnoreCase("ap")).thenReturn(items);

        List<Item> result = service.getItems("ap");
        assertEquals(1, result.size());
        assertEquals("Apple", result.get(0).getName());
    }

    @Test
    void shouldAddNewItem() {
        Item newItem = new Item("Mango");
        when(repo.save(any(Item.class))).thenReturn(newItem);

        Item saved = service.addItem("Mango");
        assertEquals("Mango", saved.getName());
    }
}
