package io.github.guimatech.cleanarchitecture.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethod {
    DEBIT_CARD(3),
    CREDIT_CARD(5);

    private int fee;
}
