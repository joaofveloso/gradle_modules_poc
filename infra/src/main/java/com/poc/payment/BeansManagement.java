package com.poc.payment;

import com.poc.payment.out.ExecuteTransaction;
import com.poc.payment.out.PersistTransactionDetails;
import com.poc.payment.usecase.ExecuteTransactionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class BeansManagement {

    @Bean
    public ExecuteTransactionUseCase executeTransactionUsecase(
            List<ExecuteTransaction> transactionExecutor, PersistTransactionDetails persistTransactionDetails) {

        Map<String, ExecuteTransaction> transactionExecuterMap = transactionExecutor.stream().collect(
                Collectors.toMap(ExecuteTransaction::id, value -> value));

        return new ExecuteTransactionUseCase(transactionExecuterMap, persistTransactionDetails);
    }
}
