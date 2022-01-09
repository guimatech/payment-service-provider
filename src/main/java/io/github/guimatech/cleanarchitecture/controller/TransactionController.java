package io.github.guimatech.cleanarchitecture.controller;

import io.github.guimatech.cleanarchitecture.model.Transaction;
import io.github.guimatech.cleanarchitecture.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.github.guimatech.cleanarchitecture.util.ConstantUtil.PATH_TRANSACTION;

@RestController
@RequestMapping(PATH_TRANSACTION)
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping
    public List<Transaction> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Transaction findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
