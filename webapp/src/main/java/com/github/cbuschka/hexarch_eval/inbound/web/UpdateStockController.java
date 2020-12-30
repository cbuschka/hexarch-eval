package com.github.cbuschka.hexarch_eval.inbound.web;

import com.github.cbuschka.hexarch_eval.domain.UpdateStockUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateStockController
{
	@Autowired
	private UpdateStockUseCase updateStockUseCase;

	@PostMapping(path = "/stockEntries")
	public ResponseEntity<?> postStockUpdate(@RequestBody UpdateStockRequest request)
	{
		updateStockUseCase.updateStock(request.getSupplierNo(),
				request.getItemNo(),
				request.getAmount(),
				request.getStockUpdatedAt());

		return ResponseEntity.noContent().build();
	}

}
