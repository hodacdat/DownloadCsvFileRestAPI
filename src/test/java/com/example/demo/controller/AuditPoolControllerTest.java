package com.example.demo.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.dto.AuditPoolDTO;
import com.example.demo.service.AuditPoolService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(AuditPoolController.class)
public class AuditPoolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuditPoolService auditPoolService;

    @Test
    public void testTestDTO() throws Exception {
        // Mock data
        List<AuditPoolDTO> mockAuditPoolDTOS = new ArrayList<>();
        // Add your mock data here...

        // Stubbing the behavior of the auditPoolService.getListAuditPoolDTO() method
        when(auditPoolService.getListAuditPoolDTO()).thenReturn(mockAuditPoolDTOS);

        // Perform the request
        mockMvc.perform(post("/hellov1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Add more assertions based on your requirements
                .andExpect(jsonPath("$").isArray());
    }
}