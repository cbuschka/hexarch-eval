package com.github.cbuschka.hexarch_eval.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class UpdateStockCommand
{
	private String supplierNo;

	private String itemNo;

	private int amount;

	private Date updatedAt;
}
