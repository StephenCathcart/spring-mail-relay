package uk.co.mailrelay.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Stephen Cathcart
 *
 */
public enum Status {
	SUCCESS("success"), ERROR("error");

	private String status;

	private Status(String status) {
		this.status = status;
	}

	@JsonValue
	public String getStatus() {
		return status;
	}

	public String toString() {
		return new ToStringBuilder(this).append("status", status).toString();
	}
}
