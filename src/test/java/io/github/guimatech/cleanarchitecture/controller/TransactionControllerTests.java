package io.github.guimatech.cleanarchitecture.controller;

import io.github.guimatech.cleanarchitecture.application.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static io.github.guimatech.cleanarchitecture.mock.TransactionMock.getSampleTransactionCredit;
import static io.github.guimatech.cleanarchitecture.mock.TransactionMock.getSampleTransactionDebit;
import static io.github.guimatech.cleanarchitecture.util.ConstantUtil.PATH_TRANSACTION;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService service;

    @Test
    void shouldReturnAllTransactions() throws Exception {
        when(service.findAll(PageRequest.of(0, 2)))
                .thenReturn(List.of(getSampleTransactionDebit(), getSampleTransactionCredit()));

        this.mockMvc.perform(get(PATH_TRANSACTION))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("[{}]"));
    }

    @Test
    void shouldReturnOneTransaction() throws Exception {
        when(service.findById(1L)).thenReturn(getSampleTransactionDebit());

        this.mockMvc.perform(get(PATH_TRANSACTION + "/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    void shouldReturnTransactionSave() throws Exception {
        var transaction = getSampleTransactionDebit();
        when(service.create(transaction)).thenReturn(transaction);

        this.mockMvc.perform(post(PATH_TRANSACTION))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }
}
