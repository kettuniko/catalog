package com.github.kopzu.catalog.service;

import com.github.kopzu.catalog.exception.ResourceNotFoundException;
import com.github.kopzu.catalog.model.Item;
import com.github.kopzu.catalog.model.ItemType;
import com.github.koraktor.steamcondenser.exceptions.SteamCondenserException;
import com.github.koraktor.steamcondenser.steam.community.SteamGame;
import com.github.koraktor.steamcondenser.steam.community.SteamId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author niko 01.03.2014
 */
@Service
public class ImportServiceImpl implements ImportService {
    @Autowired
    private DatabaseService databaseService;

    @Override
    public List<Item> persistSteamGames(String username) throws ResourceNotFoundException {
        List<Item> importedItems = new ArrayList<>();
        try {
            for (SteamGame game : SteamId.create(username).getGames().values()) {
                importedItems.add(new Item(game.getName(), ItemType.GAME, game.getLogoUrl()));
            }
        } catch (SteamCondenserException sce) {
            throw new ResourceNotFoundException(sce);
        }

        databaseService.saveItems(importedItems);
        return importedItems;
    }
}
