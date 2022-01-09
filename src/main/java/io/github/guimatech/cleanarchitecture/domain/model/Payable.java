package io.github.guimatech.cleanarchitecture.domain.model;

import io.github.guimatech.cleanarchitecture.util.MathUtil;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import static io.github.guimatech.cleanarchitecture.domain.model.PaymentMethod.CREDIT_CARD;
import static io.github.guimatech.cleanarchitecture.domain.model.PaymentMethod.DEBIT_CARD;
import static io.github.guimatech.cleanarchitecture.domain.model.StatusPayable.PAID;
import static io.github.guimatech.cleanarchitecture.domain.model.StatusPayable.WAITING_FUNDS;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Payable implements Serializable {

    @Serial
    private static final long serialVersionUID = -3229866364404335324L;

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

    public static Payable getPayableFromTransaction(Transaction transaction) {
        return switch (transaction.getPaymentMethod()) {
            case DEBIT_CARD -> Payable.builder()
                    .value(discountProcessingFee(transaction.getValue(), DEBIT_CARD))
                    .status(PAID)
                    .paymentDate(LocalDate.now())
                    .build();
            case CREDIT_CARD -> Payable.builder()
                    .value(discountProcessingFee(transaction.getValue(), CREDIT_CARD))
                    .status(WAITING_FUNDS)
                    .paymentDate(LocalDate.now().plusMonths(1))
                    .build();
        };
    }

    public static double discountProcessingFee(double value, PaymentMethod fee) {
        return value - MathUtil.getValueDiscount(value, fee.getFee());
    }
}
