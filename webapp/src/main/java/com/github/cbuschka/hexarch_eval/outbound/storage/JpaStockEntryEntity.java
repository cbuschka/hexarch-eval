package com.github.cbuschka.hexarch_eval.outbound.storage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity(name="StockEntry")
@Table(name="stock_entry")
@ToString
public class JpaStockEntryEntity
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "supplier_no")
	private String supplierNo;

	@Column(name = "item_no")
	private String itemNo;

	@Column(name = "amount")
	private int amount;

	@Column(name = "stock_updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date stockUpdatedAt;

	public JpaStockEntryEntity(String supplierNo, String itemNo)
	{
		this.supplierNo = supplierNo;
		this.itemNo = itemNo;
	}
}
