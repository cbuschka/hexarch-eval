package com.github.cbuschka.hexarch_eval.secondary;

import com.github.cbuschka.hexarch_eval.core.StockEntry;
import com.github.cbuschka.hexarch_eval.core.StockRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class JpaStockRepositoryAdapter implements StockRepository
{
	private JpaStockRepository jpaStockRepository;

	public JpaStockRepositoryAdapter(JpaStockRepository jpaStockRepository)
	{
		this.jpaStockRepository = jpaStockRepository;
	}

	@Override
	public Optional<StockEntry> findBySupplierNoAndItemNo(String supplierNo, String itemNo)
	{
		return this.jpaStockRepository.findBySupplierNoAndItemNo(supplierNo, itemNo)
				.map(this::toDto);

	}

	private StockEntry toDto(JpaStockEntryEntity entity)
	{
		return new StockEntry(entity.getSupplierNo(), entity.getItemNo(),
				entity.getAmount(), entity.getStockUpdatedAt());
	}

	@Override
	public void save(StockEntry dto)
	{
		JpaStockEntryEntity entity = this.jpaStockRepository.findBySupplierNoAndItemNo(dto.getSupplierNo(), dto.getItemNo())
				.orElseGet(() -> new JpaStockEntryEntity(dto.getSupplierNo(), dto.getItemNo()));
		entity.setAmount(dto.getAmount());
		entity.setStockUpdatedAt(dto.getStockUpdatedAt());
		this.jpaStockRepository.save(entity);

		log.info("Stock entry {} saved.", entity);
	}
}
