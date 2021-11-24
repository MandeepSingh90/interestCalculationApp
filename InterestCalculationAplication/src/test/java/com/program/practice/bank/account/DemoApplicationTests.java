package com.program.practice.bank.account;

import com.program.practice.bank.account.model.AccountDetails;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureWebTestClient
class DemoApplicationTests {

	@Value("${server.servlet.context-path}")
	String contextPath;

	@Autowired
	private WebTestClient webClient;
	static AccountDetails accountDetails  = new AccountDetails();;
	@BeforeAll
	public static void createRequestBody(){
		accountDetails.setBsb("1234");
		accountDetails.setIdentification(1234567L);
		Date date = new Date(2021, 3, 5, 0, 0);
		accountDetails.setOpeningDate(date);
	}

	@Test
	void testAddAccountController() {
				webClient.post().uri(contextPath+"accounts/open")
				.accept(MediaType.APPLICATION_JSON).body(Mono.just(accountDetails),AccountDetails.class).exchange().expectStatus().is2xxSuccessful()
				.expectBody(String.class).consumeWith((response) -> {
			assertEquals("{\"bsb\":\"1234\",\"identification\":1234567,\"openingDate\":\"3921-04-04T18:30:00.000+0000\",\"updatedAt\":\"2021-11-24T22:12:32.942+0000\"}", response.getResponseBody());
		});
	}

	@Test
	void testAlreadyExistAccountController() {
		webClient.post().uri(contextPath+"accounts/open")
				.accept(MediaType.APPLICATION_JSON).body(Mono.just(accountDetails),AccountDetails.class).exchange().expectStatus().is2xxSuccessful()
				.expectBody(String.class).consumeWith((response) -> {
					assertEquals("{\"message\":\" account is already existing \"}", response.getResponseBody());
				});
	}

	@Test
	void testDailyInterestController() {
		webClient.post().uri(contextPath+"accounts/daily-interest")
				.accept(MediaType.APPLICATION_JSON).body(Mono.just(accountDetails),AccountDetails.class).exchange().expectStatus().is2xxSuccessful();
	}

	@Test
	void testMonthelyInterestController() {
		webClient.post().uri(contextPath+"accounts/monthly-interest")
				.accept(MediaType.APPLICATION_JSON).body(Mono.just(accountDetails),AccountDetails.class).exchange().expectStatus().is2xxSuccessful();
	}
}
