package com.bgarage.inventory.controller;

import com.bgarage.inventory.InventoryApplication;
import com.bgarage.inventory.model.PartModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = InventoryApplication.class)
@AutoConfigureMockMvc
class PartsControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldSaveNewPartSuccessfully() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/bgarage/parts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"spring\"}")
                ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        PartModel response = new ObjectMapper().readValue(contentAsString, PartModel.class);
        Assertions.assertThat(response.getName()).isEqualTo("spring");
        Assertions.assertThat(response.getId()).isNotEmpty();
    }
    @Test
    public void shouldUpdatePartQuantiyForExistingPart() throws Exception {
        int availableQuantity = 100;
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/bgarage/parts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"spring\",\"quantity\":" + availableQuantity +
                                ",\"supplier\":\"Supplier-A\""+
                                ",\"thresholdLimit\":30"+
                                ",\"minimumOrderQuantity\":50"+"}"
                        )
                ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        PartModel response = objectMapper.readValue(contentAsString, PartModel.class);
        String id = response.getId();
        int usedQuantity = 10;
        MvcResult updatePartResponse = mockMvc.perform(MockMvcRequestBuilders.patch("/bgarage/parts/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"quantityUsed\":" + usedQuantity + "}")
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        String updatedPart = updatePartResponse.getResponse().getContentAsString();
        PartModel updatedQuantity = objectMapper.readValue(updatedPart, PartModel.class);

        Assertions.assertThat(updatedQuantity.getName()).isEqualTo("spring");
        Assertions.assertThat(updatedQuantity.getQuantity()).isEqualTo(availableQuantity -usedQuantity);
        Assertions.assertThat(updatedQuantity.getId()).isNotEmpty();
    }
}