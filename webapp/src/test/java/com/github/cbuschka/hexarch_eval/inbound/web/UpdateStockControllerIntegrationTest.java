package com.github.cbuschka.hexarch_eval.inbound.web;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static java.lang.Math.abs;

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
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		Random random = new Random();
		String supplierNo = String.format("s%d", abs(random.nextInt()));
		String itemNo = String.format("i#%s-%d", supplierNo, abs(random.nextInt()));

		this.mockMvc
				.perform(
						MockMvcRequestBuilders.post("/stockEntries")
								.contentType(MediaType.APPLICATION_JSON)
								.content(String.format("{\"supplierNo\": \"%s\", \"itemNo\":\"%s\",\"amount\":1,\"stockUpdatedAt\":\"%s\"}", supplierNo, itemNo, dateFormat.format(new Date()))))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}
