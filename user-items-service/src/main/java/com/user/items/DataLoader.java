package com.user.items;

import com.user.items.model.Item;
import com.user.items.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ItemRepository repo;

    public DataLoader(ItemRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        repo.save(new Item("Apple"));
        repo.save(new Item("Banana"));
        repo.save(new Item("Grapes"));
        repo.save(new Item("Mango"));
        repo.save(new Item("Orange"));
    }
}
