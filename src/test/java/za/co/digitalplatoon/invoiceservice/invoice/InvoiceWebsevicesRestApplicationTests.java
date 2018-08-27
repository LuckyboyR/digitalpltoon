package za.co.digitalplatoon.invoiceservice.invoice;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import za.co.digitalplatoon.invoiceservice.invoice.data.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.repository.InvoiceRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class InvoiceWebsevicesRestApplicationTests {
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private InvoiceRepository invoiceRepository;
    
	 /*
	  * 
	  * Test insert and get an invoice
	  * 
	*/
    @Test
    public void addInvoice() {
      
    	Invoice inv = new Invoice();
    	inv.setClient("lucky");
    	inv.setVatRate(15l);
        entityManager.persist(inv);
        entityManager.flush();
     
        // when
        Invoice invoice = invoiceRepository.getOne(inv.getId());
        // then
        assertEquals(invoice.getId(), inv.getId());

    }
	
	@Test
	public void contextLoads() {
	}
	
	

}
