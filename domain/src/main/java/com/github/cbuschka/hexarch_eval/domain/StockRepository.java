package com.github.cbuschka.hexarch_eval.domain;

import java.util.Optional;

public interface StockRepository
{
	Optional<StockEntry> findBySupplierNoAndItemNo(String supplierNo, String itemNo);

	void save(StockEntry stockEntry);
}
