package dohi.programming.assignment;

import com.fasterxml.jackson.databind.ObjectMapper;
import dohi.programming.assignment.framework.V1;
import dohi.programming.assignment.model.BasicResponse;
import dohi.programming.assignment.model.Bundle;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestfulApiApplicationTests {

	@Autowired
	private WebApplicationContext ctx;

	@Autowired
	ObjectMapper objectMapper;

	private MockMvc mockMvc;

//	private Authentication authentication;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(this.ctx)
				.build();
	}

	/**
	 * TestAllControllerStatusOk check all API calls in row
	 * @throws Exception
	 */
	@Test
	public void TestAllControllerStatusOk() throws Exception {
		//create fake bundle
		Bundle bundle = new Bundle();
		bundle.setId(1234567890);
		bundle.setName("The northern circuit");
		bundle.setInfo("This circuit paths take you along a set of Northern scenic views.");
		bundle.setImage("http://assets.example.com/bundle-1234567890.jpg");

//		String basicDigestHeaderValue = "Basic " + new String(Base64.encodeBase64(("user1:password1").getBytes()));

		// mock bundle creation
		MvcResult mvcResult = this.mockMvc.perform(
				post(V1.URI_CREATE_ABSOLUTE).accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//						.header("Authorization", basicDigestHeaderValue)
						.content(objectMapper.writeValueAsString(bundle))
				)
				.andExpect(request().asyncStarted())
				.andReturn();

		ResponseEntity<BasicResponse> basicResponseEntity = (ResponseEntity<BasicResponse>)mvcResult.getAsyncResult();
		BasicResponse basicResponse = basicResponseEntity.getBody();

		//confirm response data
		assertEquals(basicResponse.getMessage(), "Bundle created");
		assertEquals(basicResponse.getId().toString(), "1234567890");

		// mock bundle retrieve
		mvcResult = this.mockMvc.perform(
				get(V1.URI_RETRIEVE_ABSOLUTE).accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//						.header("Authorization", basicDigestHeaderValue)
						.param("id", "1234567890"))
				.andExpect(request().asyncStarted())
				.andReturn();

		ResponseEntity<Bundle> bundleResponseEntity = (ResponseEntity<Bundle>)mvcResult.getAsyncResult();
		Bundle bundleResponse = bundleResponseEntity.getBody();

		//confirm response data
		assertEquals(bundleResponse.getImage(), bundle.getImage());
		assertEquals(bundleResponse.getInfo(), bundle.getInfo());
		assertEquals(bundleResponse.getName(), bundle.getName());
		assertEquals(bundleResponse.getId(), bundle.getId());

		bundle.setName("The northern circuit updated");
		bundle.setInfo("This circuit paths take you along a set of Northern scenic views updated.");

		// mock bundle update
		mvcResult = this.mockMvc.perform(
				put(V1.URI_UPDATE_ABSOLUTE).accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//						.header("Authorization", basicDigestHeaderValue)
						.content(objectMapper.writeValueAsString(bundle))
				)
				.andExpect(request().asyncStarted())
				.andReturn();


		basicResponseEntity = (ResponseEntity<BasicResponse>)mvcResult.getAsyncResult();
		basicResponse = basicResponseEntity.getBody();

		//confirm response data
		assertEquals(basicResponse.getMessage(), "Bundle updated");
		assertEquals(basicResponse.getId().toString(), "1234567890");

		// mock bundle deletion
		mvcResult = this.mockMvc.perform(
				delete(V1.URI_DELETE_ABSOLUTE).accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//						.header("Authorization", basicDigestHeaderValue)
						.param("id", "1234567890"))
				.andExpect(request().asyncStarted())
				.andReturn();

		basicResponseEntity = (ResponseEntity<BasicResponse>)mvcResult.getAsyncResult();
		basicResponse = basicResponseEntity.getBody();

		//confirm response data
		assertEquals(basicResponse.getMessage(), "Bundle deleted");
		assertEquals(basicResponse.getId().toString(), "1234567890");
	}

}
