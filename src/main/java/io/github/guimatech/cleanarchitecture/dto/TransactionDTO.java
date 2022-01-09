package io.github.guimatech.cleanarchitecture.dto;

import io.github.guimatech.cleanarchitecture.model.PaymentMethod;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
public class TransactionDTO implements Serializable {

    private double value;
    private String description;
    private PaymentMethod paymentMethod;
    private String cardNumber;
    private String cardHolderName;
    private LocalDate expirationDate;
    private String cvv;
}
