package com.github.cbuschka.hexarch_eval.inbound.mq;

import com.github.cbuschka.hexarch_eval.domain.StaleStockDataException;
import com.github.cbuschka.hexarch_eval.domain.UpdateStockCommand;
import com.github.cbuschka.hexarch_eval.domain.UpdateStockUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UpdateStockMessageReceiver
{
	@Autowired
	private UpdateStockUseCase updateStockUseCase;

	public void onMessage(UpdateStockMessage message)
	{
		try
		{
			updateStockUseCase.updateStock(new UpdateStockCommand(message.getSupplierNo(),
					message.getItemNo(),
					message.getAmount(),
					message.getStockUpdatedAt()));
		}
		catch( StaleStockDataException ex) {
			log.info("Stale stock data: {} Update skipped.", message, ex);
		}
	}

}
