package com.poc.payment.out;

import com.poc.payment.in.ExecuteTransactionCommand.TransactionSummary;

public interface PersistTransactionDetails {

    void persistTransactionSummary(TransactionSummary transactionSummary);
}
