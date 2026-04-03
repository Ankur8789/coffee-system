package com.lld.services;

import com.lld.models.Ingredient;

public interface InventoyObserver {
    public void update(Ingredient ingredient, String message);
}
