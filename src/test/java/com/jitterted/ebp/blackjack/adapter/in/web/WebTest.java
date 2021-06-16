package com.jitterted.ebp.blackjack.adapter.in.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
public class WebTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testIndexReturns200OK() throws Exception {
        mockMvc.perform(get("/index.html"))
               .andExpect(status().isOk());
    }

    @Test
    public void testStartGameWillReturn200OK() throws Exception {
        mockMvc.perform(post("/start-game"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/game"));
    }
}
