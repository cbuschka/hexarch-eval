package com.github.cbuschka.hexarch_eval.infrastructure;

import com.github.cbuschka.hexarch_eval.core.UpdateStockUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockConfig
{
	@Bean
	public UpdateStockUseCase updateStockUseCase() {
		throw new Error("not implemented yet");
	}
}
