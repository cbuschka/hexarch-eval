package com.github.cbuschka.hexarch_eval.domain;

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

	private long versionLoaded;

	public StockEntry(String supplierNo, String itemNo)
	{
		this(supplierNo, itemNo, 0, null, 0);
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
