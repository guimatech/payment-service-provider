package io.github.guimatech.cleanarchitecture.repository;

import io.github.guimatech.cleanarchitecture.model.Payable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayableRepository extends PagingAndSortingRepository<Payable, Long> {
}
