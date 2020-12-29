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

	private Date lastModifiedAt;

	public StockEntry(String supplierNo, String itemNo)
	{
		this(supplierNo, itemNo, 0, null);
	}

	public void update(int amount, Date lastModifiedAt) throws StaleStockDataException
	{
		if (this.lastModifiedAt != null && this.lastModifiedAt.after(lastModifiedAt))
		{
			throw new StaleStockDataException();
		}

		this.amount = amount;
		this.lastModifiedAt = lastModifiedAt;
	}
}
