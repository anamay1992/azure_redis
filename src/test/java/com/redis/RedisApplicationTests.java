package com.redis;

import com.redis.mock.MockConstant;
import com.redis.mock.MockData;
import com.redis.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class RedisApplicationTests {

	@Autowired
	private ReactiveRedisTemplate<String, Employee> template;
	private ReactiveValueOperations<String, Employee> valueOps;

	@BeforeEach
	void setup() {
		this.valueOps = template.opsForValue();
	}


	@Test
	void testSetUserCache() {
		Mono<Boolean> source =
				valueOps.set(MockConstant.KEY_USER_DEFAULT, MockData.getMockEmployee());
		StepVerifier.create(source)
				.expectNext(true)
				.verifyComplete();
	}

	@Test
	void testGetUserCache() {
		Mono<Employee> source = valueOps.get(MockConstant.KEY_USER_DEFAULT);
		StepVerifier.create(source)
				.expectNext(MockData.getMockEmployee())
				.verifyComplete();
	}

	@Test
	void testDeleteUserCache() {
		Mono<Boolean> source
				= valueOps.delete(MockConstant.KEY_USER_DEFAULT);
		StepVerifier.create(source)
				.expectNext(true)
				.verifyComplete();
	}

}
