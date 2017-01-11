package uk.co.mailrelay.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import uk.co.mailrelay.exception.InvalidRequestException;
import uk.co.mailrelay.model.Request;
import uk.co.mailrelay.model.Response;
import uk.co.mailrelay.service.MailService;

/**
 * A RESTful api for sending mail.

 * @see Request
 * @see Response
 * @author Stephen Cathcart
 *
 */
@RestController
@RequestMapping(
	value = "/api", 
	produces = MediaType.APPLICATION_JSON_VALUE, 
	consumes = MediaType.APPLICATION_JSON_VALUE, 
	headers = {"content-type=application/json"})
public class MailController {

	@Autowired
    MailService mailService;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/send", method = RequestMethod.POST)	
    public Response mail(@Valid @RequestBody Request request, BindingResult bindingResult) throws InvalidRequestException {
		if(bindingResult.hasErrors()) {
			throw new InvalidRequestException(bindingResult.getFieldErrors());
		}
		return mailService.sendMail(request); 
    }
}
