package com.github.kopzu.catalog.service;

import com.github.kopzu.catalog.exception.ResourceNotFoundException;
import com.github.kopzu.catalog.model.Item;
import com.github.koraktor.steamcondenser.exceptions.SteamCondenserException;
import com.github.koraktor.steamcondenser.steam.community.SteamGame;
import com.github.koraktor.steamcondenser.steam.community.SteamId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.powermock.api.mockito.PowerMockito.*;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * @author niko 01.03.2014
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SteamId.class)
public class ImportServiceImplTest {
    public static final String PLAYER_NAME = "testPlayer";
    @Mock
    private DatabaseService databaseService;
    @InjectMocks
    private ImportServiceImpl context;
    private SteamId steamId;
    private HashMap<Integer, SteamGame> games;

    @Before
    public void setUp() throws Exception {
        mockStatic(SteamId.class);
        steamId = mock(SteamId.class);
        games = new HashMap<>();
    }

    @Test
    public void testPersistSteamGames() throws Exception {
        games.put(1, mock(SteamGame.class));
        games.put(2, mock(SteamGame.class));

        when(SteamId.create(PLAYER_NAME)).thenReturn(steamId);
        when(steamId.getGames()).thenReturn(games);

        List<Item> gamesPersisted = context.persistSteamGames(PLAYER_NAME);

        verifyStatic();
        SteamId.create(PLAYER_NAME);

        verify(steamId).getGames();
        verify(databaseService).saveItems(gamesPersisted);
        assertEquals(games.size(), gamesPersisted.size());
        verifyNoMoreInteractions(steamId, databaseService);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void resourceNotFoundExceptionWhenCondenserFails() throws Exception {
        when(SteamId.create(PLAYER_NAME)).thenThrow(new SteamCondenserException());
        context.persistSteamGames(PLAYER_NAME);
    }
}
