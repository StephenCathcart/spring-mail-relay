package uk.co.mailrelay.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import uk.co.mailrelay.model.Request;

/**
 * No need for an implementation class due to Spring Data.
 * 
 * @author Stephen Cathcart
 *
 */
@Repository
public interface MailRepository extends CrudRepository<Request, Long> {

	@SuppressWarnings("unchecked")
	public Request save(Request mail);
}
