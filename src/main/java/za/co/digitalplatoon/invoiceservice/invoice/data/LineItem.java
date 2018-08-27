/**
 * 
 */
package za.co.digitalplatoon.invoiceservice.invoice.data;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author Lucky Rapudi
 *
 */
@Entity
@Table(name="LineItem")
public class LineItem {

	@Id
	@GeneratedValue
	private Long id;
	private Long quantity;
	private String description;
	private BigDecimal unitPrice;
	@ManyToOne
	@JoinColumn(name = "invoiceId")
	private Invoice invoice;

	public LineItem() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param quantity
	 * @param description
	 * @param unitPrice
	 */
	public LineItem(Long id, Long quantity, String description, BigDecimal unitPrice) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.description = description;
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the quantity
	 */
	public Long getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the unitPrice
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice
	 *            the unitPrice to set
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	

	/**
	 * @return the invoice
	 */
	  @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "invoiceId", nullable = false)
	    @JsonBackReference
	public Invoice getInvoice() {
		return invoice;
	}

	/**
	 * @param invoice the invoice to set
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	/**
	 * @return line item total price
	 */
	public BigDecimal getLineItemTotal() {

		return getUnitPrice().multiply(new BigDecimal(getQuantity()));

	}

}
