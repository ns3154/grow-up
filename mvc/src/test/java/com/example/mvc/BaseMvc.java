package com.example.mvc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import jakarta.annotation.Resource;

@SpringBootTest(properties = "my.property=value")
@TestPropertySource(properties = {
        "my.property=value"
})
@ExtendWith(value = {SpringExtension.class})
@AutoConfigureMockMvc
public class BaseMvc {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void testGetUser() throws Exception {
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/setUp"));
        MvcResult mvcResult = perform.andReturn();
        perform.andDo(new ResultHandler() {
            @Override
            public void handle(MvcResult result) throws Exception {
                System.out.println(result.getResponse().getContentAsString());
            }
        });
    }
}
