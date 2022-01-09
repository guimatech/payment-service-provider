package io.github.guimatech.cleanarchitecture.controller;

import io.github.guimatech.cleanarchitecture.model.Transaction;
import io.github.guimatech.cleanarchitecture.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

import static io.github.guimatech.cleanarchitecture.util.ConstantUtil.PATH_TRANSACTION;

@RestController
@RequestMapping(PATH_TRANSACTION)
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping
    public List<Transaction> findAll(@RequestParam int page, int size) {
        return service.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Transaction findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Transaction create(@RequestBody Transaction transaction) {
        return service.create(transaction);
    }
}
