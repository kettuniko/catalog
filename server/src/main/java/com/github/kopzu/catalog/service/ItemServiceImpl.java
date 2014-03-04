package com.github.kopzu.catalog.service;

import com.github.kopzu.catalog.model.Item;
import com.github.kopzu.catalog.model.ItemType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author niko 04.03.2014
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public List<Item> getItems(ItemType itemType) {
        return Arrays.asList(new Item(1l, "CSS", ItemType.GAME), new Item(2l, "TF2", ItemType.GAME));
    }
}
