package com.github.kopzu.catalog.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author niko 01.03.2014
 */
@Data
@NoArgsConstructor
@Document(collection = "items")
public class Item {
    @Id
    private String id;
    private String name;
    private ItemType type;
    private String logoUrl;
    private boolean completed;

    public Item(String name, ItemType type, String logoUrl) {
        this.name = name;
        this.type = type;
        this.logoUrl = logoUrl;
    }

}
