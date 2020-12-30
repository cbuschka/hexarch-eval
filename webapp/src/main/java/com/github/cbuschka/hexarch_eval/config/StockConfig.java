package com.github.cbuschka.hexarch_eval.config;

import com.github.cbuschka.hexarch_eval.domain.StockUpdatedNotificationSender;
import com.github.cbuschka.hexarch_eval.domain.UpdateStockUseCase;
import com.github.cbuschka.hexarch_eval.outbound.notification.DummyMqStockUpdatedNotificationSender;
import com.github.cbuschka.hexarch_eval.outbound.storage.JpaStockRepository;
import com.github.cbuschka.hexarch_eval.outbound.storage.JpaStockRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = JpaStockRepository.class)
public class StockConfig
{
	@Autowired
	private JpaStockRepository jpaStockRepository;

	@Bean
	public UpdateStockUseCase updateStockUseCase()
	{
		return new UpdateStockUseCase(jpaStockRepositoryAdapter(), stockUpdatedNotifier());
	}

	@Bean
	protected StockUpdatedNotificationSender stockUpdatedNotifier()
	{
		return new DummyMqStockUpdatedNotificationSender();
	}

	@Bean
	protected JpaStockRepositoryAdapter jpaStockRepositoryAdapter()
	{
		return new JpaStockRepositoryAdapter(jpaStockRepository);
	}
}
