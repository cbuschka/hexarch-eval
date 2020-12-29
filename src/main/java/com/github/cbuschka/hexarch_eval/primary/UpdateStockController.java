package com.github.cbuschka.hexarch_eval.primary;

import com.github.cbuschka.hexarch_eval.core.UpdateStockUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateStockController
{
	@Autowired
	private TransactionTemplate transactionTemplate;
	@Autowired
	private UpdateStockUseCase updateStockUseCase;

	@PostMapping(path="/stockEntries")
	public ResponseEntity<?> postStockUpdate(@RequestBody UpdateStockRequest request) {

		transactionTemplate.execute((s) -> {
			updateStockUseCase.updateStock(request.getSupplierNo(),
					request.getItemNo(),
					request.getAmount(),
					request.getStockUpdatedAt());
			return null;
		});

		return ResponseEntity.noContent().build();
	}

}
