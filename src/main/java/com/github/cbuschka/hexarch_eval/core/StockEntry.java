package com.github.cbuschka.hexarch_eval.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class StockEntry
{
	private String supplierNo;

	private String itemNo;

	private int amount;

	private Date stockUpdatedAt;

	public StockEntry(String supplierNo, String itemNo)
	{
		this(supplierNo, itemNo, 0, null);
	}

	public void update(int amount, Date stockUpdatedAt) throws StaleStockDataException
	{
		if (this.stockUpdatedAt != null && this.stockUpdatedAt.after(stockUpdatedAt))
		{
			throw new StaleStockDataException();
		}

		this.amount = amount;
		this.stockUpdatedAt = stockUpdatedAt;
	}
}
