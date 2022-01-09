package io.github.guimatech.cleanarchitecture.api.controller;

import io.github.guimatech.cleanarchitecture.domain.model.Transaction;
import io.github.guimatech.cleanarchitecture.domain.dto.TransactionDTO;
import io.github.guimatech.cleanarchitecture.application.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.github.guimatech.cleanarchitecture.util.ConstantUtil.PATH_TRANSACTION;

@RestController
@RequestMapping(PATH_TRANSACTION)
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping
    public List<Transaction> findAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return service.findAll();
        else
            return service.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Transaction findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Transaction create(@RequestBody TransactionDTO transaction) {
        var persistentTransaction = Transaction.builder()
                .value(transaction.getValue())
                .description(transaction.getDescription())
                .paymentMethod(transaction.getPaymentMethod())
                .cardNumber(transaction.getCardNumber())
                .cardHolderName(transaction.getCardHolderName())
                .expirationDate(transaction.getExpirationDate())
                .cvv(transaction.getCvv())
                .build();
        return service.create(persistentTransaction);
    }
}
