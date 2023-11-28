package com.poc.payment.models;

import java.math.BigDecimal;

public record TransactionSummaryResponse(
        Integer sourceAccount, Integer targetAccount, BigDecimal amount, String paymentMethod, String transactionId,
        String transactionStatus) {
}
