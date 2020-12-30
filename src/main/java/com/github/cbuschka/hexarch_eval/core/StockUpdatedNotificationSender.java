package com.github.cbuschka.hexarch_eval.core;

import java.util.Date;

public interface StockUpdatedNotificationSender
{
	void notifyStockEntryUpdated(String supplierNo, String itemNo, int amount, Date stockUpdatedAt);
}
