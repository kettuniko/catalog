package com.github.kopzu.catalog.controller;

import com.github.kopzu.catalog.model.Item;
import com.github.kopzu.catalog.service.ImportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author niko 01.03.2014
 */
@RunWith(MockitoJUnitRunner.class)
public class ImportControllerTest {
    public static final String PLAYER_NAME = "testPlayer";

    @Mock
    private ImportService importService;
    @InjectMocks
    private ImportController context;

    @Test
    public void imports() throws Exception {
        when(importService.persistSteamGames(PLAYER_NAME)).thenReturn(asList(new Item(), new Item()));

        List<Item> items = context.importSteamGames(PLAYER_NAME);

        verify(importService).persistSteamGames(PLAYER_NAME);
        verifyNoMoreInteractions(importService);

        assertEquals(2, items.size());
    }


}
