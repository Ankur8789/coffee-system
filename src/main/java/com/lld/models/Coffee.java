package com.lld.models;

import java.util.HashMap;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Coffee {
    private String id;
    private String name;
    private CoffeeType type;
    private int amount;
    private HashMap<Ingredient, Integer> ingredients;

    public Coffee(String name, CoffeeType type, HashMap<Ingredient, Integer> ingreds) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.ingredients = ingreds;
    }
}
