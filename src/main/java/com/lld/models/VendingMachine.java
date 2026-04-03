package com.lld.models;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.lld.services.CoffeeService;
import com.lld.services.PaymentService;

public class VendingMachine implements CoffeeService {

    // instance
    private static VendingMachine vendingMachine;
    // hashMaps
    private ConcurrentHashMap<String, User> userMap;
    private ConcurrentHashMap<String, Coffee> coffeeMap;
    private ConcurrentHashMap<String, Bill> billmap;

    // Payment
    private PaymentFactory paymentFactory;

    // instances
    private Inventory inventory;

    // Dispenser
    private Dispenser dispenser;

    // Admin
    private User admin;

    public VendingMachine() {
        this.userMap = new ConcurrentHashMap<>();
        this.coffeeMap = new ConcurrentHashMap<>();
        this.billmap = new ConcurrentHashMap<>();

        this.inventory = Inventory.getInstance();

        TwDispenser twDispenser = new TwDispenser();
        FiveHD fiveHD = new FiveHD();
        FiFtyD fiFtyD = new FiFtyD();
        TenD tenD = new TenD();
        OneD oneD = new OneD();

        twDispenser.setNextDispenser(fiveHD);
        fiveHD.setNextDispenser(fiFtyD);
        fiFtyD.setNextDispenser(tenD);
        tenD.setNextDispenser(oneD);

        this.dispenser = twDispenser;
    }

    void setAdmin(User user) {
        this.admin = user;
    }

    public static synchronized VendingMachine getInstance() {
        if (vendingMachine == null) {
            vendingMachine = new VendingMachine();
        }
        return vendingMachine;
    }

    public void addUser(User user) {
        this.userMap.put(user.getId(), user);
    }

    @Override
    public List<Coffee> getAllCoffees() {
        return this.coffeeMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Bill getCoffee(Coffee coffee, int amount, PaymentTypes types) {
        Bill bill = Bill.builder()
                .id(UUID.randomUUID().toString())
                .coffee(coffee)
                .localDateTime(LocalDateTime.now())
                .status(Status.INPROGRESS)
                .build();

        try {

            // see if there is ample amount of ingredients
            boolean isPossible = inventory.getItems(coffee.getIngredients(), admin);
            if (!isPossible || (coffee.getAmount() > amount)) {
                throw new Exception();
            }

            // payment process
            int rem = amount - coffee.getAmount();
            PaymentService service = this.paymentFactory.getInstance(types);
            service.pay(coffee.getAmount());
            // dispense the rest
            dispenser.dispense(rem);

            bill.setStatus(Status.BOOKED);

        } catch (Exception e) {
            bill.setStatus(Status.FAILED);
        }
        this.billmap.put(bill.getId(), bill);
        return bill;
    }

}
