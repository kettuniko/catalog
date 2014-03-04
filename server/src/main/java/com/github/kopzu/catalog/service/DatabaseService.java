package com.github.kopzu.catalog.service;

import com.github.kopzu.catalog.model.Item;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

/**
 * @author niko 04.03.2014
 */
public interface DatabaseService {
    void saveItems(List<Item> items);

    MongoOperations createOperations();
}
