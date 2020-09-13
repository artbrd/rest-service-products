package ru.restserviceproducts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.restserviceproducts.dto.ClientInfoDto;
import ru.restserviceproducts.entity.Rule;
import ru.restserviceproducts.service.api.ProductService;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllProductsTest() throws Exception {
        mockMvc.perform(get("/products")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[*]", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("product_1")))
                .andExpect(jsonPath("$[0].startSumCred", is(0)))
                .andExpect(jsonPath("$[0].endSumCred", is(200000)))
                .andExpect(jsonPath("$[0].percent", is(6)))
                .andExpect(jsonPath("$[0].term", is(36)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("product_2")))
                .andExpect(jsonPath("$[1].startSumCred", is(0)))
                .andExpect(jsonPath("$[1].endSumCred", is(999999999999L)))
                .andExpect(jsonPath("$[1].percent", is(15)))
                .andExpect(jsonPath("$[1].term", is(0)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].name", is("product_3")))
                .andExpect(jsonPath("$[2].startSumCred", is(0)))
                .andExpect(jsonPath("$[2].endSumCred", is(1000000)))
                .andExpect(jsonPath("$[2].percent", is(12)))
                .andExpect(jsonPath("$[2].term", is(60)));
    }

    @Test
    public void getRulsForProductsTest() throws Exception {
        mockMvc.perform(get("/products/3/rules")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[*]", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(3)))
                .andExpect(jsonPath("$[0].startSalary", is(25000)))
                .andExpect(jsonPath("$[0].endSalary", is(999999999999L)))
                .andExpect(jsonPath("$[0].isDebt").doesNotExist());
    }

    @Test
    public void getRulsForProductsBadTest() throws Exception {
        mockMvc.perform(get("/products/4/rules"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void addRulForProductTest() throws Exception {
        Rule rule = new Rule();

        rule.setStartSalary(500000L);
        rule.setEndSalary(700000L);
        rule.setIsDebt(false);

        mockMvc.perform(post("/products/1/rules")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(rule)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addRulForProductBadTest() throws Exception {
        Rule rule = new Rule();

        rule.setStartSalary(500000L);
        rule.setEndSalary(700000L);
        rule.setIsDebt(false);

        mockMvc.perform(post("/products/4/rules")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(rule)))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void delRulFromProductTest() throws Exception {
        mockMvc.perform(delete("/products/1/rules/1")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void delRulFromProductBadTest() throws Exception {
        mockMvc.perform(delete("/products/1/rules/2"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getPoductsForClientTest() throws Exception {
        ClientInfoDto clientInfoDto = new ClientInfoDto();
        clientInfoDto.setSalary(60000L);
        clientInfoDto.setClaim(300000L);
        clientInfoDto.setIsDebtor(false);

        mockMvc.perform(post("/products/apply")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(clientInfoDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[*]", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].name", is("product_2")))
                .andExpect(jsonPath("$[0].startSumCred", is(0)))
                .andExpect(jsonPath("$[0].endSumCred", is(999999999999L)))
                .andExpect(jsonPath("$[0].percent", is(15)))
                .andExpect(jsonPath("$[0].term", is(0)))
                .andExpect(jsonPath("$[1].id", is(3)))
                .andExpect(jsonPath("$[1].name", is("product_3")))
                .andExpect(jsonPath("$[1].startSumCred", is(0)))
                .andExpect(jsonPath("$[1].endSumCred", is(1000000)))
                .andExpect(jsonPath("$[1].percent", is(12)))
                .andExpect(jsonPath("$[1].term", is(60)));
    }

    @Test
    public void getPoductsForClientBadTest() throws Exception {
        ClientInfoDto clientInfoDto = new ClientInfoDto();
        clientInfoDto.setSalary(600000L);
        clientInfoDto.setClaim(3000000L);
        clientInfoDto.setIsDebtor(true);

        mockMvc.perform(post("/products/apply")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(clientInfoDto)))
                .andDo(print())
                .andExpect(jsonPath("$.status", is("Reject")))
                .andExpect(jsonPath("$.message", is("Can not found matching product for client")))
                .andExpect(status().is4xxClientError());
    }
}
