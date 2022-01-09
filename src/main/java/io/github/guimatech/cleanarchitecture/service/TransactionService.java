package io.github.guimatech.cleanarchitecture.service;

import io.github.guimatech.cleanarchitecture.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    public List<Transaction> findAll() {
        return new ArrayList<>();
    }
}
