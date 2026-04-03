package com.lld.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lld.services.InventoyObserver;

public class Inventory {

    private static Inventory inventory;

    private HashMap<Ingredient, Integer> idegs;

    // List of subscribers (Observers)
    private List<InventoyObserver> admins;

    private Inventory() {
        this.idegs = new HashMap<>();
        this.admins = new ArrayList<>();
    }

    // Attach an observer
    public void addObserver(InventoyObserver observer) {
        this.admins.add(observer);
    }

    // Broadcast to all attached observers
    public void notifyAdmins(Ingredient ingredient, String message) {
        for (InventoyObserver admin : admins) {
            // Assuming your interface has a method named 'update' or 'onLowStock'
            // Change this method call to match exactly what is in your InventoyObserver
            // interface
            admin.update(ingredient, message);
        }
    }

    public void addItem(Ingredient ingredient, int amount, User user) {
        if (!user.isAdmin()) {
            System.out.println("Not allowed !");
            return;
        }
        if (idegs.containsKey(ingredient)) {
            idegs.put(ingredient, idegs.get(ingredient) + amount);
        } else {
            idegs.put(ingredient, amount);
        }

        System.out.println("Successfully added");
    }

    public static synchronized Inventory getInstance() {
        if (inventory == null) {
            inventory = new Inventory();
        }
        return inventory;
    }

    // Removed the 'User user' parameter to fully decouple the class
    public synchronized boolean getItems(HashMap<Ingredient, Integer> reqs) {

        boolean isOkay = true;
        for (Ingredient ingredient : reqs.keySet()) {
            if (!this.idegs.containsKey(ingredient)) {
                System.out.println("Items not present in Inventory");
                // Replaced direct user call with broadcast
                notifyAdmins(ingredient, "Item : " + ingredient + " not available");
                isOkay = false;
            } else if (this.idegs.get(ingredient) < reqs.get(ingredient)) {
                System.out.println("Items not present in Inventory");
                // Replaced direct user call with broadcast
                notifyAdmins(ingredient, "Item : " + ingredient + " is less available ! ");
                isOkay = false;
            }
        }

        if (!isOkay) {
            return isOkay;
        }

        // sab available hai aur thik thak mamle me
        for (Ingredient ingredient : reqs.keySet()) {
            this.idegs.put(ingredient, this.idegs.get(ingredient) - reqs.get(ingredient));

            if (this.idegs.get(ingredient) < 10) {
                // Replaced direct user call with broadcast
                notifyAdmins(ingredient, "Item : " + ingredient + " is running low! ");
            }
        }

        return isOkay;
    }
}