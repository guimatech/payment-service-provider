package io.github.guimatech.cleanarchitecture.application.service;

import io.github.guimatech.cleanarchitecture.domain.model.Payable;
import io.github.guimatech.cleanarchitecture.domain.model.Transaction;
import io.github.guimatech.cleanarchitecture.infrastructure.dataprovider.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public List<Transaction> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();
    }

    public List<Transaction> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Transaction findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Transaction create(Transaction transaction) {
        handleSensitiveInformation(transaction);
        transaction.setPayable(Payable.getPayableFromTransaction(transaction));
        return repository.save(transaction);
    }

    private void handleSensitiveInformation(Transaction transaction) {
        transaction.setCardNumber(transaction.getCardNumber().substring(transaction.getCardNumber().length() - 4));
    }
}
