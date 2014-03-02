package com.github.kopzu.catalog.controller;

import com.github.kopzu.catalog.model.Item;
import com.github.kopzu.catalog.service.ImportService;
import com.github.koraktor.steamcondenser.exceptions.SteamCondenserException;
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
 * @author niko 01.03.2014
 */
@RunWith(MockitoJUnitRunner.class)
public class ImportControllerTest {

    public static final String PLAYER_NAME = "testPlayer";
    @InjectMocks
    protected ImportController context;
    protected MockMvc mockMvc;
    @Mock
    private ImportService importService;

    @Before
    public void setUpBaseControllerTest() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(context).build();
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(importService);
    }

    @Test
    public void returnsImportedItems() throws Exception {
        List<Item> items = asList(new Item(), new Item());
        whenPersists().thenReturn(items);

        performSteamImport()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(items.size())));

        verify(importService).persistSteamGames(PLAYER_NAME);
    }

    private ResultActions performSteamImport() throws Exception {
        return mockMvc.perform(get("/import/steam").param("userName", PLAYER_NAME));
    }

    private OngoingStubbing<List<Item>> whenPersists() throws Exception {
        return when(importService.persistSteamGames(PLAYER_NAME));
    }

    @Test
    public void statusIsNotFoundWhenError() throws Exception {
        whenPersists().thenThrow(new SteamCondenserException());
        performSteamImport().andExpect(status().isNotFound());
        verify(importService).persistSteamGames(PLAYER_NAME);
    }
}
