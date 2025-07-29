package com.user.items.service.impl;


import com.user.items.model.Item;
import com.user.items.repository.ItemRepository;
import com.user.items.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repo;

    public ItemServiceImpl(ItemRepository repo) {
        this.repo = repo;
    }

    /**
     * Get all items or filter by name if search term is provided.
     *
     */
    @Override
    public List<Item> getItems(String filter) {
        if (filter != null && !filter.isBlank()) {
          // return repo.findAll().stream().filter( item -> item.getName().toLowerCase().contains(filter.toLowerCase())).toList();
            return repo.findByNameContainingIgnoreCase(filter);
        }
        return repo.findAll();
    }

    /**
     * Add a new item.
     */
    @Override
    public Item addItem(String name) {
        Item item = new Item(name);
        return repo.save(item);
    }
}
