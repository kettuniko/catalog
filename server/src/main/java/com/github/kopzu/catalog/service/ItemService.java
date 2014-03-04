package com.github.kopzu.catalog.service;

import com.github.kopzu.catalog.model.Item;
import com.github.kopzu.catalog.model.ItemType;

import java.util.List;

/**
 * @author niko 04.03.2014
 */
public interface ItemService {
    List<Item> getItems(ItemType itemType);
}
