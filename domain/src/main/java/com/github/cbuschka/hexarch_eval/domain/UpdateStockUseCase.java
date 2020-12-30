package com.github.cbuschka.hexarch_eval.domain;

public class UpdateStockUseCase
{
	private StockRepository stockRepository;

	private StockUpdatedNotificationSender stockUpdatedNotificationSender;

	public UpdateStockUseCase(StockRepository stockRepository, StockUpdatedNotificationSender stockUpdatedNotificationSender)
	{
		this.stockRepository = stockRepository;
		this.stockUpdatedNotificationSender = stockUpdatedNotificationSender;
	}

	public void updateStock(UpdateStockCommand command) throws StaleStockDataException
	{
		StockEntry stockEntry = this.stockRepository.findBySupplierNoAndItemNo(command.getSupplierNo(), command.getItemNo())
				.orElseGet(() -> new StockEntry(command.getSupplierNo(), command.getItemNo()));

		stockEntry.update(command.getAmount(), command.getUpdatedAt());

		stockUpdatedNotificationSender.notifyStockEntryUpdated(command.getSupplierNo(), command.getItemNo(), command.getAmount(), command.getUpdatedAt());
		stockRepository.save(stockEntry);
	}
}
