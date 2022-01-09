package io.github.guimatech.cleanarchitecture.infrastructure.dataprovider.repository;

import io.github.guimatech.cleanarchitecture.domain.model.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
}
