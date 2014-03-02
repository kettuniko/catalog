package com.github.kopzu.catalog.service;

import com.github.kopzu.catalog.model.Item;
import com.github.koraktor.steamcondenser.exceptions.SteamCondenserException;
import com.github.koraktor.steamcondenser.steam.community.SteamGame;
import com.github.koraktor.steamcondenser.steam.community.SteamId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author niko 01.03.2014
 */
@Service
public class ImportServiceImpl implements ImportService {
    @Override
    public List<Item> persistSteamGames(String userName) {
        List<Item> importedItems = new ArrayList<>();
        try {
            HashMap<Integer, SteamGame> games = SteamId.create(userName).getGames();
            // TODO replace with persistence logic
            for (Map.Entry<Integer, SteamGame> gameEntry : games.entrySet()) {
                importedItems.add(new Item(gameEntry.getKey().longValue(), gameEntry.getValue().getName()));
            }
        } catch (SteamCondenserException e) {
            throw new RuntimeException("Failed to import games.", e);
        }
        return importedItems;
    }
}
