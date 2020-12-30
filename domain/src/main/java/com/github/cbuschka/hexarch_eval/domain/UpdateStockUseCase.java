package com.github.cbuschka.hexarch_eval.domain;

import java.util.Date;

public class UpdateStockUseCase
{
	private StockRepository stockRepository;

	private StockUpdatedNotificationSender stockUpdatedNotificationSender;

	public UpdateStockUseCase(StockRepository stockRepository, StockUpdatedNotificationSender stockUpdatedNotificationSender)
	{
		this.stockRepository = stockRepository;
		this.stockUpdatedNotificationSender = stockUpdatedNotificationSender;
	}

	public void updateStock(String supplierNo, String itemNo, int amount, Date updatedAt)
	{
		StockEntry stockEntry = this.stockRepository.findBySupplierNoAndItemNo(supplierNo, itemNo)
				.orElseGet(() -> new StockEntry(supplierNo, itemNo));

		try
		{
			stockEntry.update(amount, updatedAt);

			stockUpdatedNotificationSender.notifyStockEntryUpdated(supplierNo, itemNo, amount, updatedAt);
			stockRepository.save(stockEntry);
		}
		catch (StaleStockDataException ex)
		{
			// update skipped
		}
	}
}
