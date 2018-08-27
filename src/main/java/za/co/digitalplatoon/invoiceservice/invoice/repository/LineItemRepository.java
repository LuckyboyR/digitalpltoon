/**
 * 
 */
package za.co.digitalplatoon.invoiceservice.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.digitalplatoon.invoiceservice.invoice.data.LineItem;

/**
 * @author Lucky Rapudi
 *
 */
@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long> {

}
