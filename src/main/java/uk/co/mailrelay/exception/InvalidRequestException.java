package uk.co.mailrelay.exception;

import java.util.List;

import org.springframework.validation.FieldError;

/**
 * @author Stephen Cathcart
 *
 */
public class InvalidRequestException extends Exception {

	private static final long serialVersionUID = -204892064327363901L;

	private List<FieldError> fieldErrors;

	public InvalidRequestException(List<FieldError> fieldErrors) {
		super("Invalid field arguments");
		this.fieldErrors = fieldErrors;
	}

	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}
}
