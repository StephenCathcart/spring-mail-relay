package uk.co.mailrelay.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import uk.co.mailrelay.model.Account;

/**
 * @author Stephen Cathcart
 *
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

	public Account findByUsername(String username);
}
