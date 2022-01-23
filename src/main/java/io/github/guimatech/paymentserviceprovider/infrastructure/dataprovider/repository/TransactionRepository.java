package io.github.guimatech.paymentserviceprovider.infrastructure.dataprovider.repository;

import io.github.guimatech.paymentserviceprovider.domain.model.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
}
