/**
 * 
 */
package za.co.digitalplatoon.invoiceservice.invoice.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author Lucky Rapudi
 *
 */
@Entity
@Table(name = "Invoice")
public class Invoice {

	@Id
	@GeneratedValue
	private Long id;
	private String client;
	private Long vatRate;
	private Date invoiceDate;

	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<LineItem> lineItems;

	/**
	 * @return the lineItems
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "invoice")
	@JsonManagedReference
	public List<LineItem> getLineItems() {
		return lineItems;
	}

	/**
	 * @param lineItems
	 *            the lineItems to set
	 */
	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
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
	 * @return the client
	 */
	public String getClient() {
		return client;
	}

	/**
	 * @param client
	 *            the client to set
	 */
	public void setClient(String client) {
		this.client = client;
	}

	/**
	 * @return the vatRate
	 */
	public Long getVatRate() {
		return vatRate;
	}

	/**
	 * @param vatRate
	 *            the vatRate to set
	 */
	public void setVatRate(Long vatRate) {
		this.vatRate = vatRate;
	}

	/**
	 * @return the invoiceDate
	 */
	public Date getInvoiceDate() {
		return invoiceDate;
	}

	/**
	 * @param invoiceDate
	 *            the invoiceDate to set
	 */
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	/**
	 * @return the subtotal amount
	 */
	@SuppressWarnings("null")
	public BigDecimal getSubTotal() {

		double subTotal = 0.00;
		lineItems = getLineItems();
		System.out.println("Line items : " + lineItems);
		if (!lineItems.isEmpty()) {
			for (LineItem item : lineItems) {
				subTotal += item.getLineItemTotal().doubleValue();
				System.out.println("Line total : " + item.getLineItemTotal());
			}
			return new BigDecimal(subTotal);
		} else {
			return new BigDecimal(0.00);
		}

	}

	/**
	 * @return the vat amount
	 */
	public BigDecimal getVat() {

		if (!getSubTotal().equals("") || !getSubTotal().equals(null)) {
			return (getSubTotal().multiply(new BigDecimal(getVatRate()))).divide(new BigDecimal(100));
		} else {
			return new BigDecimal(0.00);
		}
	}

	/**
	 * @return the total including vat
	 */
	public BigDecimal getTotal() {
		if (!getSubTotal().equals("") || !getSubTotal().equals(null)) {
			return getSubTotal().add(getVat());
		} else {
			return new BigDecimal(0.00);
		}

	}

}
