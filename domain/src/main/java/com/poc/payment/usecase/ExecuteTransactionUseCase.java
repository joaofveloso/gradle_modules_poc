package com.poc.payment.usecase;

import com.poc.payment.in.ExecuteTransactionCommand;
import com.poc.payment.out.ExecuteTransaction;
import com.poc.payment.out.PersistTransactionDetails;

import java.util.Map;

public class ExecuteTransactionUseCase implements ExecuteTransactionCommand {

    private final Map<String, ExecuteTransaction> paymentsMap;
    private final PersistTransactionDetails persistTransactionDetails;

    public ExecuteTransactionUseCase(
            Map<String, ExecuteTransaction> paymentsMap, PersistTransactionDetails persistTransactionDetails) {
        this.paymentsMap = paymentsMap;
        this.persistTransactionDetails = persistTransactionDetails;
    }

    @Override
    public TransactionSummary execute(TransactionDetail transactionDetail) {

        String paymentMethod = transactionDetail.paymentMethod();
        ExecuteTransaction executeTransaction = obtainPaymentProviderForSuppliedMethod(paymentMethod);
        String transactionId = executeTransaction.executeTransaction(transactionDetail);
        TransactionSummary transactionSummary = new TransactionSummary(
                transactionDetail, transactionId, TransactionStatus.SUCCESSFUL);
        this.persistTransactionDetails.persistTransactionSummary(transactionSummary);
        return transactionSummary;
    }

    private ExecuteTransaction obtainPaymentProviderForSuppliedMethod(String paymentMethod) {
        ExecuteTransaction executeTransaction = paymentsMap.get(paymentMethod);
        if (executeTransaction == null) {
            String message = String.format("The payment method '%s' is not available", paymentMethod);
            throw new RuntimeException(message);
        }
        return executeTransaction;
    }
}