package uk.co.mailrelay.model;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import uk.co.mailrelay.model.Response;
import uk.co.mailrelay.model.Status;

public class ResponseTest {

	@Test
	public void testResponseBuilder() {
		Long expectedCode = 200L;
		Status expectedStatus = Status.SUCCESS;
		String expectedMsg1 = "Msg1";
		String expectedMsg2 = "Msg2";
		
		Response response = new Response.Builder()
			.code(expectedCode)
			.status(expectedStatus)
			.messages(Arrays.asList(expectedMsg1, expectedMsg2))
			.build();

		assertEquals(response.getCode(), expectedCode);
		assertEquals(response.getStatus(), expectedStatus);
		assertEquals(response.getMessages().size(), 2);
		assertEquals(response.getMessages().get(0), expectedMsg1);
		assertEquals(response.getMessages().get(1), expectedMsg2);
	}

}
