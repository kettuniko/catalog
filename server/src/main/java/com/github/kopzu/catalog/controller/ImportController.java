package com.github.kopzu.catalog.controller;

import com.github.kopzu.catalog.exception.ResourceNotFoundException;
import com.github.kopzu.catalog.model.Item;
import com.github.kopzu.catalog.service.ImportService;
import com.github.koraktor.steamcondenser.exceptions.SteamCondenserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author niko 01.03.2014
 */
@Controller
@RequestMapping("/import")
@EnableAutoConfiguration
public class ImportController {
    @Autowired
    private ImportService importService;

    @RequestMapping("/steam")
    public
    @ResponseBody
    List<Item> importSteamGames(@RequestParam String userName) {
        try {
            return importService.persistSteamGames(userName);
        } catch (SteamCondenserException e) {
            throw new ResourceNotFoundException();
        }
    }
}
