package ru.job4j.accident.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.Main;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class AccidentControlTest {
 /*   @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void whenCreateAccidentGet() throws Exception {
        this.mockMvc.perform(get("/accidents/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/createAccident"));
    }

    @Test
    @WithMockUser
    public void whenPageNotFound() throws Exception {
        this.mockMvc.perform(get("/accidents/page-not-found"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("pageNotFound"));
    }

    @Test
    @WithMockUser
    public void whenWithoutRule() throws Exception {
        this.mockMvc.perform(get("/accidents/set-rule"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("setRule"));
    }

    @Test
    @WithMockUser
    public void whenAccidentGet() throws Exception {
        String id = "1";
        this.mockMvc.perform(get("/accidents/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/accident")
                );
    }

    @Test
    @WithMockUser
    public void whenUpdateGet() throws Exception {
        this.mockMvc.perform(get("/accidents/update").queryParam("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/updateAccident"));
    }
     */
}