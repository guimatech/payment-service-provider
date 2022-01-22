package io.github.guimatech.cleanarchitecture.api.controller;

import io.github.guimatech.cleanarchitecture.application.service.TransactionService;
import io.github.guimatech.cleanarchitecture.domain.dto.TransactionRequestDTO;
import io.github.guimatech.cleanarchitecture.domain.dto.TransactionResponseDTO;
import io.github.guimatech.cleanarchitecture.domain.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static io.github.guimatech.cleanarchitecture.util.ConstantUtil.PATH_TRANSACTION;

@RestController
@RequestMapping(PATH_TRANSACTION)
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> findAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        List<Transaction> transactions;
        if (page == null || size == null)
            transactions = service.findAll();
        else
            transactions = service.findAll(PageRequest.of(page, size));
        return new ResponseEntity<>(transactions.stream().map(TransactionResponseDTO::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id).transformToDTO(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> create(@RequestBody TransactionRequestDTO dto) {
        Transaction transaction = service.create(dto.transformToObject());
        return new ResponseEntity<>(transaction.transformToDTO(), HttpStatus.CREATED);
    }
}
