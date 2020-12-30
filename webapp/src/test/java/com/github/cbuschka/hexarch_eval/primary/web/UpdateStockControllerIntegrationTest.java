package com.github.cbuschka.hexarch_eval.primary.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class UpdateStockControllerIntegrationTest
{
	@Autowired
	private WebApplicationContext context;

	protected MockMvc mockMvc;

	@BeforeEach
	public void setup()
	{
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(this.context)
				.build();
	}

	@Test
	public void shouldCreateUnknownStockEntry() throws Exception
	{
		this.mockMvc
				.perform(
						MockMvcRequestBuilders.post("/stockEntries")
								.contentType(MediaType.APPLICATION_JSON)
								.content("{\"supplierNo\": \"s0\", \"itemNo\":\"i0\",\"amount\":1,\"stockUpdatedAt\":\"2020-01-01T15:33+01:00\"}"))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}
