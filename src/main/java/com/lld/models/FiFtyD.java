package com.lld.models;

public class FiFtyD extends Dispenser {

    @Override
    public void dispense(int amount) {
        int val = amount / 50;
        if (val > 0) {
            System.out.println("Dispensing " + val + " Notes of 50 !");
        }
        this.nextDispenser.dispense(amount % 50);
    }

}
