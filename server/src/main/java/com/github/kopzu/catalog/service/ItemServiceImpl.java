package com.github.kopzu.catalog.service;

import com.github.kopzu.catalog.model.Item;
import com.github.kopzu.catalog.model.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author niko 04.03.2014
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private DatabaseService databaseService;

    @Override
    public List<Item> getItems(ItemType itemType) {
        MongoOperations operations = databaseService.createOperations();
        Query searchUserQuery = new Query(Criteria.where("type").is(itemType));
        return operations.find(searchUserQuery, Item.class);
    }
}
