package com.github.kopzu.catalog.service;

import com.github.kopzu.catalog.exception.ResourceNotFoundException;
import com.github.kopzu.catalog.model.Item;

import java.util.List;

/**
 * @author niko 01.03.2014
 */
public interface ImportService {
    /**
     * Persists steam games of given user
     *
     * @param username steam username (needs to be public)
     * @return list of new games persisted
     */
    List<Item> persistSteamGames(String username) throws ResourceNotFoundException;
}
