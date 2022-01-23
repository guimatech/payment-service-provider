package io.github.guimatech.paymentserviceprovider.mock;

import io.github.guimatech.paymentserviceprovider.domain.dto.TransactionRequestDTO;

import static io.github.guimatech.paymentserviceprovider.domain.model.PaymentMethod.CREDIT_CARD;
import static io.github.guimatech.paymentserviceprovider.domain.model.PaymentMethod.DEBIT_CARD;

public class TransactionRequestDTOMock {

    private TransactionRequestDTOMock() {}

    public static TransactionRequestDTO getSampleTransactionRequestDTODebit() {
        return TransactionRequestDTO.builder()
                .value(12)
                .paymentMethod(DEBIT_CARD)
                .build();
    }

    public static TransactionRequestDTO getSampleTransactionRequestDTOCredit() {
        return TransactionRequestDTO.builder()
                .value(20)
                .paymentMethod(CREDIT_CARD)
                .build();
    }
}
