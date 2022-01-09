package io.github.guimatech.cleanarchitecture.repository;

import io.github.guimatech.cleanarchitecture.model.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
}
