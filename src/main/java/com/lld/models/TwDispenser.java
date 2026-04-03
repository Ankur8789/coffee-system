package com.lld.models;

public class TwDispenser extends Dispenser {

    @Override
    public void dispense(int amount) {
        int val = (amount / 2000);
        if (val > 0) {
            System.out.println("Dispensing " + val + " notes of 2000");
        }
        if (this.nextDispenser != null)
            this.nextDispenser.dispense(amount % 2000);
    }

}
