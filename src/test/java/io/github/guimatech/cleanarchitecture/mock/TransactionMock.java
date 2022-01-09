package io.github.guimatech.cleanarchitecture.mock;

import io.github.guimatech.cleanarchitecture.domain.model.Transaction;

import static io.github.guimatech.cleanarchitecture.domain.model.PaymentMethod.CREDIT_CARD;
import static io.github.guimatech.cleanarchitecture.domain.model.PaymentMethod.DEBIT_CARD;

public class TransactionMock {

    private TransactionMock() {}

    public static Transaction getSampleTransactionDebit() {
        return Transaction.builder()
                .id(1L)
                .value(12)
                .paymentMethod(DEBIT_CARD)
                .build();
    }

    public static Transaction getSampleTransactionCredit() {
        return Transaction.builder()
                .id(1L)
                .value(20)
                .paymentMethod(CREDIT_CARD)
                .build();
    }
}
