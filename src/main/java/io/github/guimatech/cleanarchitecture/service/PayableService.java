package io.github.guimatech.cleanarchitecture.service;

import io.github.guimatech.cleanarchitecture.model.Balance;
import io.github.guimatech.cleanarchitecture.model.Payable;
import io.github.guimatech.cleanarchitecture.repository.PayableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return new Balance();
    }
}
