package com.poc.payment.in;

import java.math.BigDecimal;

public interface ExecuteTransactionCommand {

    TransactionSummary execute(TransactionDetail transactionDetail);

    record TransactionDetail(Integer sourceAccount, Integer targetAccount, BigDecimal amount, String paymentMethod){}

    record TransactionSummary(
            TransactionDetail transactionDetail, String transactionId, TransactionStatus transactionStatus){}

    enum TransactionStatus{
        SUCCESSFUL,
        FAILED
    }
}