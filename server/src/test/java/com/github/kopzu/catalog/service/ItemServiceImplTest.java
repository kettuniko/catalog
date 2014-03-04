package com.github.kopzu.catalog.service;

import com.github.kopzu.catalog.model.Item;
import com.github.kopzu.catalog.model.ItemType;
import com.mongodb.DBObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author niko 04.03.2014
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemServiceImplTest {
    @Mock
    private DatabaseService databaseService;
    @InjectMocks
    private ItemServiceImpl context;

    @Test
    public void getsItemsWithCriteria() throws Exception {
        MongoOperations operations = mock(MongoOperations.class);
        when(operations.find(any(Query.class), eq(Item.class))).thenReturn(new ArrayList<Item>());

        ArgumentCaptor<Query> queryCaptor = ArgumentCaptor.forClass(Query.class);

        when(databaseService.createOperations()).thenReturn(operations);
        ItemType type = ItemType.GAME;
        context.getItems(type);
        verify(operations).find(queryCaptor.capture(), eq(Item.class));
        verify(databaseService).createOperations();
        verifyNoMoreInteractions(databaseService);
        // Only one criteria
        DBObject queryObject = queryCaptor.getValue().getQueryObject();
        assertEquals(1, queryObject.keySet().size());
        assertEquals(type, queryObject.get("type"));
    }
}
