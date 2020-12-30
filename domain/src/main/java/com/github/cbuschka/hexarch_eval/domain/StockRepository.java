package com.github.cbuschka.hexarch_eval.domain;

import java.util.Optional;

public interface StockRepository
{
	StockEntry create(String supplierNo, String itemNo);

	Optional<StockEntry> findBySupplierNoAndItemNo(String supplierNo, String itemNo);

	void save(StockEntry stockEntry);
}
