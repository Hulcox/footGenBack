package fr.sdv.cnit.university.api.controller;

import fr.sdv.cnit.university.api.entity.PlayerEntity;
import fr.sdv.cnit.university.api.entity.TeamEntity;
import fr.sdv.cnit.university.api.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Assertions;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeamController.class)
class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamController teamController;

    @BeforeEach
    void setUp() {
        // You can perform setup operations here if needed
    }

    @Test
    void shouldReturnHelloWorld() throws Exception {
        mockMvc.perform(get("/teams/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"));
    }

    @Test
    void shouldReturnAllTeams() throws Exception {
        when(teamService.getAllTeams()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/teams/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        // Add assertions based on your actual response content
    }

    @Test
    void shouldReturnTeamById() throws Exception {
        // Implement the behavior of teamService.getTeamById() based on your needs
        // when(teamService.getTeamById(anyLong())).thenReturn(...);

        mockMvc.perform(get("/teams/{teamId}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        // Add assertions based on your actual response content
    }

    @Test
    void shouldAddTeam() throws Exception {
        mockMvc.perform(post("/teams/add")
                        .param("name", "TeamName")
                        .param("slogan", "TeamSlogan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[]"))
                .andExpect(status().isOk())
                .andExpect(content().string("Team added successfully"));
    }

    @Test
    void shouldReturnBadRequestWhenAddTeamWithEmptySlogan() throws Exception {
        // Mock the behavior to throw TeamInvalidException

        TeamEntity team = new TeamEntity();

        doThrow(new fr.sdv.cnit.university.api.exception.TeamInvalidException("Slogan cannot be null or empty"))
                .when(teamService).createTeam(team);

        var result = mockMvc.perform(post("/teams/add")
                        .param("name", "TeamName")
                        .param("slogan", "")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[]"))
                .andExpect(status().isBadRequest())
                .andReturn();

        Assertions.assertEquals("Slogan cannot be null or empty", result.getResolvedException().getMessage());
    }

    @Test
    void shouldUpdateTeam() throws Exception {
        mockMvc.perform(put("/teams/update/{teamId}", 1L)
                        .param("name", "NewTeamName")
                        .param("slogan", "NewTeamSlogan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[]"))
                .andExpect(status().isOk())
                .andExpect(content().string("Team updated successfully"));
    }

    @Test
    void shouldReturnBadRequestWhenUpdateTeamWithEmptySlogan() throws Exception {
        // Mock the behavior to throw TeamInvalidException
        doThrow(new fr.sdv.cnit.university.api.exception.TeamInvalidException("Slogan cannot be null or empty"))
                .when(teamService).updateTeam(anyLong(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString());

        var result = mockMvc.perform(put("/teams/update/{teamId}", 1L)
                        .param("name", "NewTeamName")
                        .param("slogan", "")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[]"))
                .andExpect(status().isBadRequest())
                .andReturn();

        Assertions.assertEquals("Slogan cannot be null or empty", result.getResolvedException().getMessage());
    }

    @Test
    void shouldDeleteTeam() throws Exception {
        mockMvc.perform(delete("/teams/delete/{teamId}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Team deleted successfully"));
    }

}
