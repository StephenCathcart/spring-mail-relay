package uk.co.mailrelay.security;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import uk.co.mailrelay.model.Response;
import uk.co.mailrelay.model.Status;

@Component
public class RESTAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex)
			throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().println(new ObjectMapper().writeValueAsString(new Response.Builder().code(401L)
				.status(Status.ERROR).messages(Arrays.asList(ex.getMessage())).build()));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("MailRelay");
		super.afterPropertiesSet();
	}
}