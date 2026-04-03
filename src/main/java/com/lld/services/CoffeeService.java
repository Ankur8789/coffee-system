package com.lld.services;

import java.util.*;

import com.lld.models.*;;

public interface CoffeeService {

    public List<Coffee> getAllCoffees();

    public Bill getCoffee(Coffee coffee, int amount, PaymentTypes type);

}
