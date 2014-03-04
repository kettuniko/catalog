package com.github.kopzu.catalog.controller;

import com.github.kopzu.catalog.exception.ResourceNotFoundException;
import com.github.kopzu.catalog.model.Item;
import com.github.kopzu.catalog.model.ItemType;
import com.github.kopzu.catalog.service.ItemService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author niko 04.03.2014
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
    @InjectMocks
    protected ItemController context;
    protected MockMvc mockMvc;
    @Mock
    private ItemService itemService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(context).build();
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(itemService);
    }

    @Test
    public void returnsItems() throws Exception {
        List<Item> items = asList(new Item(), new Item());
        whenGetItems().thenReturn(items);

        performGetItems()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(items.size())));

        verify(itemService).getItems(ItemType.GAME);
    }

    private ResultActions performGetItems() throws Exception {
        return mockMvc.perform(get("/items").param("type", "GAME"));
    }

    private OngoingStubbing<List<Item>> whenGetItems() throws Exception {
        return when(itemService.getItems(ItemType.GAME));
    }

    @Test
    public void statusIsNotFoundWhenError() throws Exception {
        whenGetItems().thenThrow(new ResourceNotFoundException());
        performGetItems().andExpect(status().isNotFound());
        verify(itemService).getItems(ItemType.GAME);
    }
}
