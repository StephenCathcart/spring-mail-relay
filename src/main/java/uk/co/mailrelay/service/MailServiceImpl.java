package uk.co.mailrelay.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import uk.co.mailrelay.model.Request;
import uk.co.mailrelay.model.Response;
import uk.co.mailrelay.model.Status;
import uk.co.mailrelay.repository.MailRepository;

/**
 * @author Stephen Cathcart
 *
 */
@Service
public class MailServiceImpl implements MailService {

	@Value("${mail.message.success200}")
	private String messageSuccess200;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MailRepository mailRepository;

	@Override
	public Response sendMail(Request mail) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(mail.getFrom());
		mailMessage.setTo(mail.getTo().toArray(new String[mail.getTo().size()]));
		if (mail.getCc() != null) {
			mailMessage.setCc(mail.getCc().toArray(new String[mail.getCc().size()]));
		}
		if (mail.getBcc() != null) {
			mailMessage.setBcc(mail.getBcc().toArray(new String[mail.getBcc().size()]));
		}
		mailMessage.setSubject(mail.getSubject());
		mailMessage.setText(mail.getMessage());
		mailMessage.setReplyTo(mail.getReplyTo());

		// Send email
		mailSender.send(mailMessage);

		// Audit email
		mailRepository.save(mail);

		return new Response.Builder().code(200L).status(Status.SUCCESS).messages(Arrays.asList(messageSuccess200))
				.build();
	}
}
