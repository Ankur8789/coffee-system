package com.lld.models;

import java.util.HashMap;

import com.lld.services.PaymentService;

public class PaymentFactory {
    private static HashMap<PaymentTypes, PaymentService> payHashMap;

    static {
        payHashMap = new HashMap<>();
        payHashMap.put(PaymentTypes.CASH, new CashPayment());
    }

    public PaymentService getInstance(PaymentTypes type) {
        return payHashMap.get(type);
    }
}
