package com.poly.lab5.service;

import com.poly.lab5.model.Item;
import java.util.Collection;

public interface ShoppingCartService {
    Item add(Integer id);
    void remove(Integer id);
    Item update(Integer id, int qty);
    void clear();
    Collection<Item> getItems();
    int getCount();
    double getAmount();
}
