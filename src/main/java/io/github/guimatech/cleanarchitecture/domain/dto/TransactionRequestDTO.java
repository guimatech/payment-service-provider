package io.github.guimatech.cleanarchitecture.domain.dto;

import io.github.guimatech.cleanarchitecture.domain.model.PaymentMethod;
import io.github.guimatech.cleanarchitecture.domain.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDTO implements Serializable {

    private double value;
    private String description;
    private PaymentMethod paymentMethod;
    private String cardNumber;
    private String cardHolderName;
    private LocalDate expirationDate;
    private String cvv;

    public TransactionRequestDTO(Transaction transaction) {
        this.value = transaction.getValue();
        this.description = transaction.getDescription();
        this.paymentMethod = transaction.getPaymentMethod();
        this.cardNumber = transaction.getCardNumber();
        this.cardHolderName = transaction.getCardHolderName();
        this.expirationDate = transaction.getExpirationDate();
        this.cvv = transaction.getCvv();
    }

    public Transaction transformToObject() {
        return new Transaction(this);
    }
}
