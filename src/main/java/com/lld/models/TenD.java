package com.lld.models;

public class TenD extends Dispenser {

    @Override
    public void dispense(int amount) {
        int val = amount / 10;
        if (val > 0) {
            System.out.println("Dispensing " + val + " Notes of 10 !");
        }
        if (this.nextDispenser != null)
            this.nextDispenser.dispense(amount % 10);
    }

}
