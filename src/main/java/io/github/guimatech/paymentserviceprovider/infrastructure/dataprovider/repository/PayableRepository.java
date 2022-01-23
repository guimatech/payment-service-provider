package io.github.guimatech.paymentserviceprovider.infrastructure.dataprovider.repository;

import io.github.guimatech.paymentserviceprovider.domain.model.Payable;
import io.github.guimatech.paymentserviceprovider.domain.model.StatusPayable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayableRepository extends PagingAndSortingRepository<Payable, Long> {

    List<Payable> findByStatus(StatusPayable statusPayable);
}
