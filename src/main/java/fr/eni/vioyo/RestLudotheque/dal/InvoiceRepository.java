package fr.eni.vioyo.RestLudotheque.dal;

import fr.eni.vioyo.RestLudotheque.bo.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
