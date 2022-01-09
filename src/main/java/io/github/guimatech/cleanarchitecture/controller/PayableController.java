package io.github.guimatech.cleanarchitecture.controller;

import io.github.guimatech.cleanarchitecture.model.Balance;
import io.github.guimatech.cleanarchitecture.model.Payable;
import io.github.guimatech.cleanarchitecture.model.Transaction;
import io.github.guimatech.cleanarchitecture.service.PayableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.github.guimatech.cleanarchitecture.util.ConstantUtil.PATH_PAYABLE;

@RestController
@RequestMapping(PATH_PAYABLE)
public class PayableController {

    @Autowired
    private PayableService service;

    @GetMapping
    public List<Payable> findAll(@RequestParam int page, int size) {
        return service.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Payable findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/balance")
    public Balance findBalance() {
        return service.findBalance();
    }
}
