package com.github.cbuschka.hexarch_eval.inbound.mq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Random;

import static java.lang.Math.abs;

@SpringBootTest
class UpdateStockMessageReceiverIntegrationTest
{
	@Autowired
	private UpdateStockMessageReceiver updateStockMessageReceiver;

	@Test
	public void shouldCreateUnknownStockEntry() throws Exception
	{
		Random random = new Random();
		String supplierNo = String.format("s%d", abs(random.nextInt()));
		String itemNo = String.format("i#%s-%d", supplierNo, abs(random.nextInt()));

		UpdateStockMessage message = new UpdateStockMessage(supplierNo, itemNo, 1, new Date());
		updateStockMessageReceiver.onMessage(message);
	}
}
