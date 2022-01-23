package io.github.guimatech.paymentserviceprovider.domain.dto;

import io.github.guimatech.paymentserviceprovider.domain.model.PaymentMethod;
import io.github.guimatech.paymentserviceprovider.domain.model.Transaction;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
public class TransactionResponseDTO implements Serializable {

    private double value;
    private String description;
    private PaymentMethod paymentMethod;
    private String cardNumber;
    private String cardHolderName;
    private LocalDate expirationDate;

    public TransactionResponseDTO(Transaction transaction) {
        this.value = transaction.getValue();
        this.description = transaction.getDescription();
        this.paymentMethod = transaction.getPaymentMethod();
        this.cardNumber = transaction.getCardNumber();
        this.cardHolderName = transaction.getCardHolderName();
        this.expirationDate = transaction.getExpirationDate();
    }
}
