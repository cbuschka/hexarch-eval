package com.github.cbuschka.hexarch_eval.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
@EnableTransactionManagement
public class TransactionConfig
{
	@Bean
	public TransactionTemplate transactionTemplate(PlatformTransactionManager platformTransactionManager)
	{
		return new TransactionTemplate(platformTransactionManager);
	}
}
