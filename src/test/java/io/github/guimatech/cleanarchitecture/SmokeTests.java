package io.github.guimatech.cleanarchitecture;

import io.github.guimatech.cleanarchitecture.controller.PayableController;
import io.github.guimatech.cleanarchitecture.controller.TransactionController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SmokeTests {

    @Autowired
    private PayableController payableController;

    @Autowired
    private TransactionController transactionController;

    @Test
    public void contextLoads() {
        assertThat(payableController).isNotNull();
        assertThat(transactionController).isNotNull();
    }
}
