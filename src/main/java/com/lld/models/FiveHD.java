package com.lld.models;

public class FiveHD extends Dispenser {

    @Override
    public void dispense(int amount) {
        int val = amount / 500;
        if (val > 0) {
            System.out.println("Dispensing " + val + " Notes of 500 !");
        }
        if (this.nextDispenser != null)
            this.nextDispenser.dispense(amount % 500);
    }

}
