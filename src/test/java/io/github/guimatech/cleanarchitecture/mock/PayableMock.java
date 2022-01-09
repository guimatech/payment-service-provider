package io.github.guimatech.cleanarchitecture.mock;

import io.github.guimatech.cleanarchitecture.domain.model.Payable;

import java.time.LocalDate;

import static io.github.guimatech.cleanarchitecture.domain.model.StatusPayable.PAID;
import static io.github.guimatech.cleanarchitecture.domain.model.StatusPayable.WAITING_FUNDS;

public class PayableMock {

    private PayableMock() {}

    public static Payable getSamplePayableDebit() {
        return Payable.builder()
                .id(1L)
                .value(12)
                .paymentDate(LocalDate.now())
                .status(PAID)
                .build();
    }

    public static Payable getSamplePayableCredit() {
        return Payable.builder()
                .id(1L)
                .value(20)
                .paymentDate(LocalDate.now())
                .status(WAITING_FUNDS)
                .build();
    }
}
