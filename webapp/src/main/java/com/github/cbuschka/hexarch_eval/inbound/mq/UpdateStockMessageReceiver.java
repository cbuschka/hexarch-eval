package com.github.cbuschka.hexarch_eval.inbound.mq;

import com.github.cbuschka.hexarch_eval.domain.UpdateStockCommand;
import com.github.cbuschka.hexarch_eval.domain.UpdateStockUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateStockMessageReceiver
{
	@Autowired
	private UpdateStockUseCase updateStockUseCase;

	public void onMessage(UpdateStockMessage message)
	{
		updateStockUseCase.updateStock(new UpdateStockCommand(message.getSupplierNo(),
				message.getItemNo(),
				message.getAmount(),
				message.getStockUpdatedAt()));
	}

}
