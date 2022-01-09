package io.github.guimatech.cleanarchitecture.service;

import io.github.guimatech.cleanarchitecture.model.Balance;
import io.github.guimatech.cleanarchitecture.model.Payable;
import io.github.guimatech.cleanarchitecture.model.StatusPayable;
import io.github.guimatech.cleanarchitecture.repository.PayableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.github.guimatech.cleanarchitecture.model.StatusPayable.PAID;
import static io.github.guimatech.cleanarchitecture.model.StatusPayable.WAITING_FUNDS;

@Service
public class PayableService {

    @Autowired
    private PayableRepository repository;

    public List<Payable> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();
    }

    public Payable findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Balance findBalance() {
        var payables = repository.findBalance();

        return Balance.builder()
                .available(getPayableValue(PAID, payables))
                .waitingFunds(getPayableValue(WAITING_FUNDS, payables))
                .build();
    }

    private double getPayableValue(StatusPayable statusPayable, List<Payable> payables) {
        var value = 0.0;
        var payableFound = payables.stream().filter(payable -> payable.getStatus() == statusPayable).findFirst().orElse(null);
        if (payableFound != null)
            value = payableFound.getValue();
        return value;
    }
}
