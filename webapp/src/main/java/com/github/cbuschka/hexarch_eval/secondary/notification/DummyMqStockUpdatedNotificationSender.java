package com.github.cbuschka.hexarch_eval.secondary.notification;

import com.github.cbuschka.hexarch_eval.domain.StockUpdatedNotificationSender;

import java.util.Date;

public class DummyMqStockUpdatedNotificationSender implements StockUpdatedNotificationSender
{
	@Override
	public void notifyStockEntryUpdated(String supplierNo, String itemNo, int amount, Date stockUpdatedAt)
	{
		// would have sent event to mq system
	}
}
