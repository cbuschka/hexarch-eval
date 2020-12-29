package com.github.cbuschka.hexarch_eval.core;

import java.util.Date;

public class UpdateStockUseCase
{
	private StockRepository stockRepository;

	public UpdateStockUseCase(StockRepository stockRepository)
	{
		this.stockRepository = stockRepository;
	}

	public void updateStock(String supplierNo, String itemNo, int amount, Date stockAt)
	{
		StockEntry stockEntry = this.stockRepository.findBySupplierNoAndItemNo(supplierNo, itemNo)
				.orElseGet(() -> this.stockRepository.create(supplierNo, itemNo));

		try
		{
			stockEntry.update(amount, stockAt);
		}
		catch (StaleStockDataException ex)
		{
			// update skipped
		}
	}
}