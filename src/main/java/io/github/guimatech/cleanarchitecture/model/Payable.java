package io.github.guimatech.cleanarchitecture.model;

import io.github.guimatech.cleanarchitecture.exception.PayableException;
import io.github.guimatech.cleanarchitecture.util.MathUtil;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

import static io.github.guimatech.cleanarchitecture.model.PaymentMethod.CREDIT_CARD;
import static io.github.guimatech.cleanarchitecture.model.PaymentMethod.DEBIT_CARD;
import static io.github.guimatech.cleanarchitecture.model.StatusPayable.PAID;
import static io.github.guimatech.cleanarchitecture.model.StatusPayable.WAITING_FUNDS;

@Data
@Table
@Entity
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Payable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private double value;

    @NotNull
    private StatusPayable status;

    @NotNull
    private LocalDate paymentDate;

    public Payable getPayableFromTransaction(Transaction transaction) {
        switch (transaction.getPaymentMethod()) {
            case DEBIT_CARD:
                return Payable.builder()
                        .value(discountProcessingRate(transaction.getValue(), DEBIT_CARD))
                        .status(PAID)
                        .paymentDate(LocalDate.now())
                        .build();
            case CREDIT_CARD:
                return Payable.builder()
                        .value(discountProcessingRate(transaction.getValue(), CREDIT_CARD))
                        .status(WAITING_FUNDS)
                        .paymentDate(LocalDate.now().plusMonths(1))
                        .build();
            default:
                throw new PayableException("Método de pagamento não reconhecido.");
        }
    }

    public double discountProcessingRate(double value, PaymentMethod fee) {
        return value - MathUtil.getValueDiscount(value, fee.getFee());
    }
}
