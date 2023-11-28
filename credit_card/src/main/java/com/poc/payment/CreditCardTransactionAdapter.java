package com.poc.payment;

import com.poc.payment.in.ExecuteTransactionCommand;
import com.poc.payment.out.ExecuteTransaction;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This class just simulates a payment method for CREDIT_CARD
 */
@Service
public class CreditCardTransactionAdapter implements ExecuteTransaction {

    @Override
    public String id() {
        return "CREDIT_CARD";
    }

    @Override
    public String executeTransaction(ExecuteTransactionCommand.TransactionDetail transactionDetail) {

        ScheduledExecutorService schedule = Executors.newSingleThreadScheduledExecutor();
        schedule.schedule(() -> {
            System.out.println("Sleeping ZzZzZzZz");
        }, 23, TimeUnit.MILLISECONDS);
        schedule.shutdown();
        return UUID.randomUUID().toString();
    }
}