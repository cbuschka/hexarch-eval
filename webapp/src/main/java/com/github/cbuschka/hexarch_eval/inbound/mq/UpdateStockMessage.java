package com.github.cbuschka.hexarch_eval.inbound.mq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UpdateStockMessage
{
	private String supplierNo;

	private String itemNo;

	private int amount;

	private Date stockUpdatedAt;
}
