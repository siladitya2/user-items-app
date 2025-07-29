package com.user.items.controller;

import com.user.items.model.Item;
import com.user.items.service.impl.ItemServiceImpl;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000") // allow React frontend
public class ItemController {


    private final ItemServiceImpl service;

    public ItemController(ItemServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/items")
    public List<Item> getItems(@RequestParam(value = "filter",required = false) String filter) {
        return service.getItems(filter);
    }

    @PostMapping("/item")
    public Item addItem(@Valid @RequestBody Item item) {
        return service.addItem(item.getName());
    }
}
