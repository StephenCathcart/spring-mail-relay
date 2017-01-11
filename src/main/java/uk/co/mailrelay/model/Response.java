package uk.co.mailrelay.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Stephen Cathcart
 *
 */
public class Response {
	private Long code;
	private Status status;
	private List<String> messages;

	private Response(Builder builder) {
		code = builder.code;
		status = builder.status;
		messages = builder.messages;
	}

	public static class Builder {
		private Long code;
		private Status status;
		private List<String> messages;

		public Builder() {
		}

		public Builder code(Long code) {
			this.code = code;
			return this;
		}

		public Builder status(Status success) {
			this.status = success;
			return this;
		}

		public Builder messages(List<String> messages) {
			this.messages = messages;
			return this;
		}

		public Response build() {
			return new Response(this);
		}
	}

	public Long getCode() {
		return code;
	}

	public Status getStatus() {
		return status;
	}

	public List<String> getMessages() {
		return messages;
	}

	public String toString() {
		return new ToStringBuilder(this).append("code", code).append("status", status).append("messages", messages)
				.toString();
	}
}
