package com.github.cbuschka.hexarch_eval.secondary.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaStockRepository extends JpaRepository<JpaStockEntryEntity, Long>
{
	Optional<JpaStockEntryEntity> findBySupplierNoAndItemNo(String supplierNo, String itemNo);
}
