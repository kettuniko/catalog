package com.github.kopzu.catalog.controller;

import com.github.kopzu.catalog.model.Item;
import com.github.kopzu.catalog.model.ItemType;
import com.github.kopzu.catalog.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author niko 04.03.2014
 */
@Controller
@EnableAutoConfiguration
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/items")
    public
    @ResponseBody
    List<Item> importSteamGames(@RequestParam("type") ItemType type) {
        return itemService.getItems(type);
    }
}
