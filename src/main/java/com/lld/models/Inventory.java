package com.lld.models;

import java.util.HashMap;

public class Inventory {

    private static Inventory inventory;

    private HashMap<Ingredient, Integer> idegs;

    private Inventory() {
        this.idegs = new HashMap<>();
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

    public synchronized boolean getItems(HashMap<Ingredient, Integer> reqs, User user) {

        boolean isOkay = true;
        for (Ingredient ingredient : reqs.keySet()) {
            if (!this.idegs.containsKey(ingredient)) {
                System.out.println("Items not present in Inventory");
                user.notify("Item : " + ingredient + "not available");
                isOkay = false;
            }
            if (this.idegs.get(ingredient) < reqs.get(ingredient)) {
                System.out.println("Items not present in Inventory");
                user.notify("Item : " + ingredient + "is less available ! ");
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
                user.notify("Item : " + ingredient + "is less available ! ");
            }
        }

        return isOkay;
    }
}
