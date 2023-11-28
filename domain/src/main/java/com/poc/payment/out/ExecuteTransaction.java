package com.poc.payment.out;

import com.poc.payment.in.ExecuteTransactionCommand.TransactionDetail;

public interface ExecuteTransaction {

    String id();
    String executeTransaction(TransactionDetail transactionDetail);
}
