package com.poc.payment.models;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer sourceAccount, Integer targetAccount, BigDecimal amount, PaymentMethodOptions paymentMethod) {
}
