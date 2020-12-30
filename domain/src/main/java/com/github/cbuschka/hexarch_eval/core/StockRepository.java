package com.github.cbuschka.hexarch_eval.core;

import java.util.Optional;

public interface StockRepository
{
	Optional<StockEntry> findBySupplierNoAndItemNo(String supplierNo, String itemNo);

	void save(StockEntry stockEntry);
}
