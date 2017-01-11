package uk.co.mailrelay.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mail.MailSendException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import uk.co.mailrelay.model.Response;
import uk.co.mailrelay.model.Status;

/**
 * @author Stephen Cathcart
 *
 */
@ControllerAdvice(annotations = RestController.class)
public class MailExceptionHandler {
	
	@Value("${mail.message.success200}")
    private String messageSuccess200;
	
	@Value("${mail.message.success200NoAudit}")
    private String messageSuccess200NoAudit;
	
	@Value("${mail.message.error400}")
    private String messageError400;
	
	@Value("${mail.message.error500}")
    private String messageError500;
	
	@Value("${mail.message.error503Mail}")
    private String messageError503Mail;
	
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Response handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		return new Response.Builder()
			.code(400L)
			.status(Status.ERROR)
			.messages(Arrays.asList(messageError400))
			.build();
	}
	
	@ExceptionHandler(value = InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response handleInvalidRequestException(InvalidRequestException ex) {
		List<String> errors = new ArrayList<>();
		for(FieldError error : ex.getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
        return new Response.Builder()
	        .code(400L)
			.status(Status.ERROR)
			.messages(errors)
			.build();
    }
	
	@ExceptionHandler(value = MailSendException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	@ResponseBody
	public Response handleMailSendException(MailSendException ex) {
		return new Response.Builder()
			.code(503L)
			.status(Status.ERROR)
			.messages(Arrays.asList(messageError503Mail))
			.build();
	}
	
	@ExceptionHandler(value = CannotCreateTransactionException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Response handleCannotCreateTransactionException(CannotCreateTransactionException  ex) {
		return new Response.Builder()
			.code(200L)
			.status(Status.SUCCESS)
			.messages(Arrays.asList(messageSuccess200, messageSuccess200NoAudit))
			.build();
	}
	
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response handleException(Exception ex) {
        return new Response.Builder()
	        .code(500L)
			.status(Status.ERROR)
			.messages(Arrays.asList(messageError500, ex.getMessage()))
			.build();
    }
}
