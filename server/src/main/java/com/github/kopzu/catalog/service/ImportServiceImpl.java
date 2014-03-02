package com.github.kopzu.catalog.service;

import com.github.kopzu.catalog.model.Item;
import com.github.kopzu.catalog.model.ItemType;
import com.github.koraktor.steamcondenser.exceptions.SteamCondenserException;
import com.github.koraktor.steamcondenser.steam.community.SteamGame;
import com.github.koraktor.steamcondenser.steam.community.SteamId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author niko 01.03.2014
 */
@Service
public class ImportServiceImpl implements ImportService {
    @Override
    public List<Item> persistSteamGames(String userName) throws SteamCondenserException {
        List<Item> importedItems = new ArrayList<>();
        // TODO replace with persistence logic
        for (Map.Entry<Integer, SteamGame> game : SteamId.create(userName).getGames().entrySet()) {
            importedItems.add(new Item(game.getKey().longValue(), game.getValue().getName(), ItemType.GAME));
        }

        return importedItems;
    }
}
