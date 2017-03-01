package dohi.programming.assignment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import dohi.programming.assignment.controller.CreateBundle;
import dohi.programming.assignment.framework.BundleStorage;
import dohi.programming.assignment.framework.V1;
import dohi.programming.assignment.model.BasicResponse;
import dohi.programming.assignment.model.Bundle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

//	@Autowired
//	private CreateBundle createBundle;
//
//	@Autowired
//	private BundleStorage bundleStorage;

	@Autowired
	ObjectMapper objectMapper;

	private MockMvc mockMvc;

	private static final Faker FAKER = new Faker();

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
	}

	@Test
	public void TestAllControllerStatusOk() throws Exception {
		Bundle bundle = new Bundle();
		bundle.setId(1234567890);
		bundle.setName("The northern circuit");
		bundle.setInfo("This circuit paths take you along a set of Northern scenic views.");
		bundle.setImage("http://assets.example.com/bundle-1234567890.jpg");

		MvcResult mvcResult = this.mockMvc.perform(
				post(V1.URI_CREATE_ABSOLUTE).accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.content(objectMapper.writeValueAsString(bundle))
				)
				.andExpect(request().asyncStarted())
				.andReturn();

		ResponseEntity<BasicResponse> basicResponseEntity = (ResponseEntity<BasicResponse>)mvcResult.getAsyncResult();
		BasicResponse basicResponse = basicResponseEntity.getBody();

		//confirm no null data
		assertEquals(basicResponse.getMessage(), "Bundle created");
		assertEquals(basicResponse.getId().toString(), "1234567890");

		mvcResult = this.mockMvc.perform(
				get(V1.URI_RETRIEVE_ABSOLUTE).accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.param("id", "1234567890"))
				.andExpect(request().asyncStarted())
				.andReturn();

		ResponseEntity<Bundle> bundleResponseEntity = (ResponseEntity<Bundle>)mvcResult.getAsyncResult();
		Bundle bundleResponse = bundleResponseEntity.getBody();

		assertEquals(bundleResponse.getImage(), bundle.getImage());
		assertEquals(bundleResponse.getInfo(), bundle.getInfo());
		assertEquals(bundleResponse.getName(), bundle.getName());
		assertEquals(bundleResponse.getId(), bundle.getId());

		bundle.setName("The northern circuit updated");
		bundle.setInfo("This circuit paths take you along a set of Northern scenic views updated.");

		mvcResult = this.mockMvc.perform(
				put(V1.URI_UPDATE_ABSOLUTE).accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.content(objectMapper.writeValueAsString(bundle))
				)
				.andExpect(request().asyncStarted())
				.andReturn();


		basicResponseEntity = (ResponseEntity<BasicResponse>)mvcResult.getAsyncResult();
		basicResponse = basicResponseEntity.getBody();

		assertEquals(basicResponse.getMessage(), "Bundle updated");
		assertEquals(basicResponse.getId().toString(), "1234567890");

		mvcResult = this.mockMvc.perform(
				delete(V1.URI_DELETE_ABSOLUTE).accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.param("id", "1234567890"))
				.andExpect(request().asyncStarted())
				.andReturn();

		basicResponseEntity = (ResponseEntity<BasicResponse>)mvcResult.getAsyncResult();
		basicResponse = basicResponseEntity.getBody();

		assertEquals(basicResponse.getMessage(), "Bundle deleted");
		assertEquals(basicResponse.getId().toString(), "1234567890");
	}

}
