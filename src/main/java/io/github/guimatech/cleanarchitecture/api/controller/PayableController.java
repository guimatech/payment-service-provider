package io.github.guimatech.cleanarchitecture.api.controller;

import io.github.guimatech.cleanarchitecture.application.service.PayableService;
import io.github.guimatech.cleanarchitecture.domain.model.Balance;
import io.github.guimatech.cleanarchitecture.domain.model.Payable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.github.guimatech.cleanarchitecture.util.ConstantUtil.PATH_PAYABLE;

@RestController
@RequestMapping(PATH_PAYABLE)
public class PayableController {

    @Autowired
    private PayableService service;

    @GetMapping
    public ResponseEntity<List<Payable>> findAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        List<Payable> payables;
        if (page == null || size == null)
            payables = service.findAll();
        else
            payables = service.findAll(PageRequest.of(page, size));
        return new ResponseEntity<>(payables, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payable> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Balance> findBalance() {
        return new ResponseEntity<>(service.findBalance(), HttpStatus.OK);
    }
}
