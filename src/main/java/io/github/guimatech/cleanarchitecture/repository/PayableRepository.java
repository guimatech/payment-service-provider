package io.github.guimatech.cleanarchitecture.repository;

import io.github.guimatech.cleanarchitecture.model.Payable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayableRepository extends PagingAndSortingRepository<Payable, Long> {

    @Query(value = "SELECT status, sum(value) as value FROM payable group by status", nativeQuery = true)
    List<Payable> findBalance();
}
