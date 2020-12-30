package com.github.cbuschka.hexarch_eval.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateStockUseCaseTest
{
	private static final String A_SUPPLIER_NO = "s0";
	private static final String AN_ITEM_NO = "i0";
	private static final int AN_AMOUNT = 7;
	private static final Date AN_UPDATE_DATE = new Date();


	@InjectMocks
	private UpdateStockUseCase useCase;

	@Mock
	private StockRepository stockRepository;
	@Mock
	private StockUpdatedNotificationSender stockUpdatedNotificationSender;
	@Mock
	private StockEntry stockEntry;

	private UpdateStockCommand updateStockCommand;

	@Test
	void shouldCreateAndSaveNonExistingStockEntry() throws StaleStockDataException
	{
		givenStockEntryDoesNotExist();
		givenUpdateStockCommand();

		whenCommandExecuted();

		thenStockEntryHasBeenUpdated();
		thenStockEntryHasBeenSaved();
		thenStockUpdateNotificationHasBeenSent();
	}

	@Test
	void shouldUpdateAndSaveExistingStockEntry() throws StaleStockDataException
	{
		givenStockEntryExists();
		givenUpdateStockCommand();

		whenCommandExecuted();

		thenStockEntryHasBeenUpdated();
		thenStockEntryHasBeenSaved();
		thenStockUpdateNotificationHasBeenSent();
	}

	private void givenStockEntryExists()
	{
		when(this.stockRepository.findBySupplierNoAndItemNo(A_SUPPLIER_NO, AN_ITEM_NO)).thenReturn(Optional.of(stockEntry));
	}

	private void thenStockUpdateNotificationHasBeenSent()
	{
		verify(this.stockUpdatedNotificationSender).notifyStockEntryUpdated(A_SUPPLIER_NO, AN_ITEM_NO, AN_AMOUNT, AN_UPDATE_DATE);
	}

	private void thenStockEntryHasBeenUpdated() throws StaleStockDataException
	{
		verify(this.stockEntry).update(AN_AMOUNT, AN_UPDATE_DATE);
	}

	private void thenStockEntryHasBeenSaved()
	{
		verify(this.stockRepository).save(stockEntry);
	}

	private void whenCommandExecuted() throws StaleStockDataException
	{
		this.useCase.updateStock(this.updateStockCommand);
	}

	private void givenUpdateStockCommand()
	{
		this.updateStockCommand = new UpdateStockCommand(A_SUPPLIER_NO, AN_ITEM_NO, AN_AMOUNT, AN_UPDATE_DATE);
	}

	private void givenStockEntryDoesNotExist()
	{
		when(this.stockRepository.findBySupplierNoAndItemNo(A_SUPPLIER_NO, AN_ITEM_NO)).thenReturn(Optional.empty());
		when(this.stockRepository.create(A_SUPPLIER_NO, AN_ITEM_NO)).thenReturn(this.stockEntry);
	}
}