package com.github.kopzu.catalog.service;

import com.github.kopzu.catalog.config.SpringMongoConfig;
import com.github.kopzu.catalog.model.Item;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author niko 04.03.2014
 */
@Service
public class DatabaseServiceImpl implements DatabaseService {
    @Override
    public void saveItems(List<Item> items) {
        MongoOperations operations = createOperations();
        operations.insertAll(items);
    }

    @Override
    public MongoOperations createOperations() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        return (MongoOperations) ctx.getBean("mongoTemplate");
    }

}
