package com.github.cbuschka.hexarch_eval.primary.mq;

import com.github.cbuschka.hexarch_eval.domain.UpdateStockUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class UpdateStockMessageReceiver
{
	@Autowired
	private TransactionTemplate transactionTemplate;
	@Autowired
	private UpdateStockUseCase updateStockUseCase;

	public void onMessage(UpdateStockMessage message)
	{
		transactionTemplate.execute((s) -> {
			updateStockUseCase.updateStock(message.getSupplierNo(),
					message.getItemNo(),
					message.getAmount(),
					message.getStockUpdatedAt());
			return null;
		});
	}

}
