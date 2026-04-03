package com.lld.models;

import com.lld.services.InventoyObserver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User implements InventoyObserver {
    private String id;
    private String name;
    private String emailId;
    private String contactNo;
    private boolean isAdmin;

    public void notify(String message) {
        // notification sent to the user
    }

    @Override
    public void update(Ingredient ingredient, String message) {
        System.out.println("Notifying user " + this.name + ": " + message);
    }
}
