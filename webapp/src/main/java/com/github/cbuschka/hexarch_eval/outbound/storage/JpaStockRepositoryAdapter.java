package com.github.cbuschka.hexarch_eval.outbound.storage;

import com.github.cbuschka.hexarch_eval.domain.StockEntry;
import com.github.cbuschka.hexarch_eval.domain.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Transactional
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
				entity.getAmount(), entity.getStockUpdatedAt(), entity.getVersion());
	}

	@Override
	public void save(StockEntry dto)
	{
		JpaStockEntryEntity entity = this.jpaStockRepository.findBySupplierNoAndItemNo(dto.getSupplierNo(), dto.getItemNo())
				.orElseGet(() -> new JpaStockEntryEntity(dto.getSupplierNo(), dto.getItemNo()));
		if (dto.getVersionLoaded() != entity.getVersion())
		{
			throw new ConcurrencyFailureException("Stock entry has been modified in the mean time. Try again.");
		}
		entity.setAmount(dto.getAmount());
		entity.setStockUpdatedAt(dto.getStockUpdatedAt());
		entity.incVersion();
		this.jpaStockRepository.save(entity);

		log.info("Stock entry {} saved.", entity);
	}
}
