package uk.co.mailrelay.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import cz.jirutka.validator.collection.constraints.EachPattern;
import uk.co.mailrelay.validator.Patterns;

/**
 * @author Stephen Cathcart
 *
 */
@Entity
@Table(name = "request")
public class Request {

	private Long id;
	private String from;
	private List<String> to;
	private List<String> cc;
	private List<String> bcc;
	private String subject;
	private String message;
	private String replyTo;
	private Date createdDate;

	@PrePersist
	protected void onCreate() {
		createdDate = new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Email
	@NotBlank
	@Column(name = "email_from")
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	@NotNull
	@EachPattern(regexp = Patterns.EMAIL_PATTERN, message = "not a well-formed email address")
	@ElementCollection
	@Column(name = "email_to")
	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	@EachPattern(regexp = Patterns.EMAIL_PATTERN, message = "not a well-formed email address")
	@ElementCollection
	@Column(name = "email_cc")
	public List<String> getCc() {
		return cc;
	}

	public void setCc(List<String> cc) {
		this.cc = cc;
	}

	@EachPattern(regexp = Patterns.EMAIL_PATTERN, message = "not a well-formed email address")
	@ElementCollection
	@Column(name = "email_bcc")
	public List<String> getBcc() {
		return bcc;
	}

	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
	}

	@NotBlank
	@Transient
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@NotBlank
	@Transient
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Pattern(regexp = Patterns.EMAIL_PATTERN, message = "not a well-formed email address")
	@Transient
	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", updatable = false)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String toString() {
		return new ToStringBuilder(this).append("from", from).append("to", to).append("cc", cc).append("bcc", bcc)
				// .append("subject", subject) Do not log subject for privacy
				// .append("message", message) Do not log message for privacy
				.append("replyTo", replyTo).append("createdDate", createdDate).toString();
	}
}
