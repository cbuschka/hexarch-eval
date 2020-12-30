package com.github.cbuschka.hexarch_eval.inbound.mq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class UpdateStockMessageReceiverIntegrationTest
{
	@Autowired
	private UpdateStockMessageReceiver updateStockMessageReceiver;

	@Test
	public void shouldCreateUnknownStockEntry() throws Exception
	{
		UpdateStockMessage message = new UpdateStockMessage("s0", "i0", 1, new Date());
		updateStockMessageReceiver.onMessage(message);
	}
}
