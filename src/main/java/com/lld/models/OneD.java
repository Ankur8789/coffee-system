package com.lld.models;

public class OneD extends Dispenser {

    @Override
    public void dispense(int amount) {
        int val = amount;
        if (val > 0) {
            System.out.println("Dispensing " + val + " coins of 1!");
        }

    }

}
