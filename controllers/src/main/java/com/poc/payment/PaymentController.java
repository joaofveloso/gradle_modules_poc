package com.poc.payment;

import com.poc.payment.in.ExecuteTransactionCommand;
import com.poc.payment.in.ExecuteTransactionCommand.TransactionDetail;
import com.poc.payment.in.ExecuteTransactionCommand.TransactionSummary;
import com.poc.payment.models.PaymentRequest;
import com.poc.payment.models.TransactionSummaryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final ExecuteTransactionCommand executeTransactionCommand;

    public PaymentController(ExecuteTransactionCommand executeTransactionCommand) {
        this.executeTransactionCommand = executeTransactionCommand;
    }

    @PostMapping
    ResponseEntity<TransactionSummaryResponse> executePayment(@RequestBody @Validated PaymentRequest request) {

        TransactionDetail command = new TransactionDetail(
                request.sourceAccount(), request.targetAccount(), request.amount(), request.paymentMethod().name());
        TransactionSummary execute = this.executeTransactionCommand.execute(command);
        TransactionSummaryResponse transactionSummaryResponse = createResponseFromTransactionSummary(execute);
        return ResponseEntity.ok(transactionSummaryResponse);
    }

    private static TransactionSummaryResponse createResponseFromTransactionSummary(TransactionSummary execute) {
        TransactionDetail transactionDetail = execute.transactionDetail();
        return new TransactionSummaryResponse(
                transactionDetail.sourceAccount(), transactionDetail.targetAccount(), transactionDetail.amount(),
                transactionDetail.paymentMethod(), execute.transactionId(), execute.transactionStatus().name());
    }
}
