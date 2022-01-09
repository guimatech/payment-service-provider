package io.github.guimatech.cleanarchitecture.api.controller;

import io.github.guimatech.cleanarchitecture.domain.model.Balance;
import io.github.guimatech.cleanarchitecture.domain.model.Payable;
import io.github.guimatech.cleanarchitecture.application.service.PayableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.github.guimatech.cleanarchitecture.util.ConstantUtil.PATH_PAYABLE;

@RestController
@RequestMapping(PATH_PAYABLE)
public class PayableController {

    @Autowired
    private PayableService service;

    @GetMapping
    public List<Payable> findAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return service.findAll();
        else
            return service.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Payable findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping(value = "/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public Balance findBalance() {
        return service.findBalance();
    }
}
