package io.github.guimatech.cleanarchitecture.domain.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transaction implements Serializable {

    @Serial
    private static final long serialVersionUID = 5693314637401810363L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private double value;

    @NotNull
    private String description;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private String cardNumber;

    @NotNull
    private String cardHolderName;

    @NotNull
    private LocalDate expirationDate;

    @NotNull
    @Size(max = 3)
    private String cvv;

    @NotNull
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="payable_id")
    private Payable payable;
}
