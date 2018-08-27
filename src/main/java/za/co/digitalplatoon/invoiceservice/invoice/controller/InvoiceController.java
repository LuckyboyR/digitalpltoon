/**
 * 
 */
package za.co.digitalplatoon.invoiceservice.invoice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import za.co.digitalplatoon.invoiceservice.invoice.data.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.repository.InvoiceRepository;
import za.co.digitalplatoon.invoiceservice.invoice.repository.LineItemRepository;

/**
 * @author Lucky Rapudi
 *
 */
@RestController
public class InvoiceController {
	@Autowired
	private InvoiceRepository invoiceRepository;
	private LineItemRepository lineItemRepository;
	
	@PostMapping("/invoices")
	public ResponseEntity<Object> addInvoice(@RequestBody Invoice invoice) {
		Invoice invoiceSave = invoiceRepository.save(invoice);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(invoiceSave.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@GetMapping("/invoices")
	public List<Invoice> viewAllInvoices() {
		return invoiceRepository.findAll();
	}

	@GetMapping("/invoices/{id}")
	public Invoice viewInvoice(@PathVariable long id) {
		Optional<Invoice> invoice = invoiceRepository.findById(id);

		return invoice.get();
	}

}
