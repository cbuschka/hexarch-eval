package com.github.cbuschka.hexarch_eval.core;

import java.util.Date;

public class UpdateStockUseCase
{
	private StockRepository stockRepository;

	public UpdateStockUseCase(StockRepository stockRepository)
	{
		this.stockRepository = stockRepository;
	}

	public void updateStock(String supplierNo, String itemNo, int amount, Date updatedAt)
	{
		StockEntry stockEntry = this.stockRepository.findBySupplierNoAndItemNo(supplierNo, itemNo)
				.orElseGet(() -> new StockEntry(supplierNo, itemNo));

		try
		{
			stockEntry.update(amount, updatedAt);
			stockRepository.save(stockEntry);
		}
		catch (StaleStockDataException ex)
		{
			// update skipped
		}
	}
}
