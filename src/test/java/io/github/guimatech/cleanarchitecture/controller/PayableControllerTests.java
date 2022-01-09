package io.github.guimatech.cleanarchitecture.controller;

import io.github.guimatech.cleanarchitecture.application.service.PayableService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static io.github.guimatech.cleanarchitecture.mock.PayableMock.getSamplePayableCredit;
import static io.github.guimatech.cleanarchitecture.mock.PayableMock.getSamplePayableDebit;
import static io.github.guimatech.cleanarchitecture.util.ConstantUtil.PATH_PAYABLE;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PayableControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PayableService service;

    @Test
    void shouldReturnAllTransactions() throws Exception {
        when(service.findAll(PageRequest.of(0, 2)))
                .thenReturn(List.of(getSamplePayableDebit(), getSamplePayableCredit()));

        this.mockMvc.perform(get(PATH_PAYABLE))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("[{}]"));
    }

    @Test
    void shouldReturnOneTransaction() throws Exception {
        when(service.findById(1L)).thenReturn(getSamplePayableDebit());

        this.mockMvc.perform(get(PATH_PAYABLE + "/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }
}
