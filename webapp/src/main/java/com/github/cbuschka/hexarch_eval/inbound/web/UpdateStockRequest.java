package com.github.cbuschka.hexarch_eval.inbound.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class UpdateStockRequest
{
	private String supplierNo;

	private String itemNo;

	private int amount;

	private Date stockUpdatedAt;
}
