package com.example.demo.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PwhTest{


    @Resource
    private MockMvc mockMvc;

//	POST /hello/{user}/{sqlId}

    @Test
    public void helloTest() throws Exception {

//    	mockMvc.perform(MockMvcRequestBuilders.post("/dataApi/add").contentType(MediaType.APPLICATION_JSON)
//                .content(JSONObject.toJSONString(dataApiModifyDto)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();

//        mockMvc.perform(MockMvcRequestBuilders.post("/dataApi/query").contentType(MediaType.APPLICATION_JSON)
//                .param("id",String.valueOf(id)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();

        mockMvc.perform(MockMvcRequestBuilders.post("/hello/pwh/123").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
	}
}