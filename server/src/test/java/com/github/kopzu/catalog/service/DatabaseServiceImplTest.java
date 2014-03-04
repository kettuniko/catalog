package com.github.kopzu.catalog.service;

import com.github.kopzu.catalog.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author niko 04.03.2014
 */
@RunWith(MockitoJUnitRunner.class)
public class DatabaseServiceImplTest {

    private DatabaseServiceImpl context;

    @Before
    public void setUp() throws Exception {
        context = spy(new DatabaseServiceImpl());
    }

    @Test
    public void insertsCollection() throws Exception {
        List<Item> items = Arrays.asList(new Item(), new Item());

        MongoOperations operations = mock(MongoOperations.class);
        doReturn(operations).when(context).createOperations();

        context.saveItems(items);
        verify(operations).insertAll(items);
        verifyNoMoreInteractions(operations);
    }

    @Test
    public void createsOperations() throws Exception {
        assertTrue(context.createOperations() != null);
    }
}
