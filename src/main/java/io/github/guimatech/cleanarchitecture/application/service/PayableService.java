package io.github.guimatech.cleanarchitecture.application.service;

import io.github.guimatech.cleanarchitecture.domain.model.Balance;
import io.github.guimatech.cleanarchitecture.domain.model.Payable;
import io.github.guimatech.cleanarchitecture.infrastructure.dataprovider.repository.PayableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static io.github.guimatech.cleanarchitecture.domain.model.StatusPayable.PAID;
import static io.github.guimatech.cleanarchitecture.domain.model.StatusPayable.WAITING_FUNDS;

@Service
public class PayableService {

    @Autowired
    private PayableRepository repository;

    public List<Payable> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();
    }

    public List<Payable> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Payable findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Balance findBalance() {
        return Balance.builder()
                .available(repository.findByStatus(PAID).stream().map(Payable::getValue).reduce(0.0, Double::sum))
                .waitingFunds(repository.findByStatus(WAITING_FUNDS).stream().map(Payable::getValue).reduce(0.0, Double::sum))
                .build();
    }
}
