package com.poc.payment;

import com.poc.payment.in.ExecuteTransactionCommand;
import com.poc.payment.in.ExecuteTransactionCommand.TransactionSummary;
import org.springframework.stereotype.Repository;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Repository
public class PersistTransactionDetails implements com.poc.payment.out.PersistTransactionDetails {

    /**
     * mock that simulates a resource being saved to a database
     * @param transactionSummary
     */
    @Override
    public void persistTransactionSummary(TransactionSummary transactionSummary) {

        ScheduledExecutorService schedule = Executors.newSingleThreadScheduledExecutor();
        schedule.schedule(() -> {
            System.out.println("Sleeping ZzZzZzZz");
        }, 23, TimeUnit.MILLISECONDS);
        schedule.shutdown();
    }
}
