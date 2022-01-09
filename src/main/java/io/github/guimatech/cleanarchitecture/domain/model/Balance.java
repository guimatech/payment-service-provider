package io.github.guimatech.cleanarchitecture.domain.model;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class Balance implements Serializable {

    private double available;
    private double waitingFunds;
}
