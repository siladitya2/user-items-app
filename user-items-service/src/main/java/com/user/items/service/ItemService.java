package com.user.items.service;

import com.user.items.model.Item;

import java.util.List;



public interface ItemService {

    /**
     * Fetch a list of items, optionally filtered by search term.
     *
     * @param filter search term (nullable)
     * @return items
     */
    List<Item> getItems(String filter);

    /**
     * Create a new item.
     *
     * @param name item name
     * @return saved item
     */
    Item addItem(String name);
}
