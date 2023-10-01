package com.example.venlyhometest.controller;

import com.example.venlyhometest.dto.RelationshipRequest;
import com.example.venlyhometest.dto.RelationshipType;
import com.example.venlyhometest.entity.Relationship;
import com.example.venlyhometest.repo.RelationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class WordControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RelationRepository relationRepository;

    @Test
    public void testAddRelationship() throws Exception {
        RelationshipRequest request = new RelationshipRequest("w1", "w2", RelationshipType.SYNONYM);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/relationship/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetRelation() throws Exception {
        List<Relationship> mockRelationships = new ArrayList<>();
        mockRelationships.add(new Relationship("word1", "word2", RelationshipType.SYNONYM));
        mockRelationships.add(new Relationship("word3", "word4", RelationshipType.ANTONYM));

        when(relationRepository.findAll()).thenReturn(mockRelationships);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/relationship/get-words-relation")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].w1").value("word1"))
                .andExpect(jsonPath("$[0].w2").value("word2"))
                .andExpect(jsonPath("$[0].relationshipType").value("synonym"))
                .andExpect(jsonPath("$[1].w1").value("word3"))
                .andExpect(jsonPath("$[1].w2").value("word4"))
                .andExpect(jsonPath("$[1].relationshipType").value("antonym"));
    }
}