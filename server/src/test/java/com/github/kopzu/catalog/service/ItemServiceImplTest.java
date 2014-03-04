package com.github.kopzu.catalog.service;

import com.github.kopzu.catalog.model.ItemType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author niko 04.03.2014
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemServiceImplTest {
    @InjectMocks
    private ItemServiceImpl context;

    @Test
    public void returnsTwoItems() throws Exception {
        Assert.assertEquals(2, context.getItems(ItemType.GAME).size());
    }
}
