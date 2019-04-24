package com.example.demo.controller;

import com.example.demo.controller.MvcMockTest;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import org.junit.Test;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;

public class ContractVerifierTest extends MvcMockTest {

	@Test
	public void validate_test() throws Exception {
		// given:
			MockMvcRequestSpecification request = given();

		// when:
			ResponseOptions response = given().spec(request)
					.queryParam("name","zhangsan")
					.get("/hello");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).isEqualTo("text/plain;charset=ISO-8859-1");
		// and:
			String responseBody = response.getBody().asString();
			assertThat(responseBody).isEqualTo("123456");
	}

}
