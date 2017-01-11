package uk.co.mailrelay.service;

import uk.co.mailrelay.model.Request;
import uk.co.mailrelay.model.Response;

/**
 * @author Stephen Cathcart
 *
 */
public interface MailService {
	public Response sendMail(Request email);
}
