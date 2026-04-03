package com.lld.models;

public abstract class Dispenser {
    protected Dispenser nextDispenser;

    public void setNextDispenser(Dispenser ds) {
        this.nextDispenser = ds;
    }

    public abstract void dispense(int amount);
}
