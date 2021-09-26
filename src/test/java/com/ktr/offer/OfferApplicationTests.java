package com.ktr.offer;

import com.ktr.offer.domain.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class OfferApplicationTests {

	@Autowired
	private UserController userController;

	@Test
	void contextLoads() {
		assertThat(userController).isNotNull();
	}

}
