package com.github.cbuschka.hexarch_eval.outbound.storage;

import com.github.cbuschka.hexarch_eval.domain.StockEntry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.ConcurrencyFailureException;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class JpaStockRepositoryAdapterIntegrationTest
{

	@Autowired
	private JpaStockRepositoryAdapter repositoryAdapter;

	@Test
	public void shouldDetectConcurrentModification() throws Exception
	{
		assertThrows(ConcurrencyFailureException.class, () -> {
			StockEntry firstStockEntry = this.repositoryAdapter.findBySupplierNoAndItemNo("s0", "i0").orElseGet(() -> new StockEntry("s0", "i0"));
			firstStockEntry.update(1, new Date());
			this.repositoryAdapter.save(firstStockEntry);

			this.repositoryAdapter.save(firstStockEntry);
		});
	}
}
