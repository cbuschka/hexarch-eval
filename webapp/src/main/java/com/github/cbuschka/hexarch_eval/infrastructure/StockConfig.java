package com.github.cbuschka.hexarch_eval.infrastructure;

import com.github.cbuschka.hexarch_eval.domain.StockUpdatedNotificationSender;
import com.github.cbuschka.hexarch_eval.domain.UpdateStockUseCase;
import com.github.cbuschka.hexarch_eval.secondary.notification.DummyMqStockUpdatedNotificationSender;
import com.github.cbuschka.hexarch_eval.secondary.storage.JpaStockRepository;
import com.github.cbuschka.hexarch_eval.secondary.storage.JpaStockRepositoryAdapter;
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
