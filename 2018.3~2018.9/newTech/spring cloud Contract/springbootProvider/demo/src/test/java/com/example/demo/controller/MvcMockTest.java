package com.example.demo.controller;

import com.example.demo.controller.HelloController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;

public class MvcMockTest {
    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new HelloController());
    }
}