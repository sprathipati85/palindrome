package com.cmegroup.palindrome.rest;

import com.cmegroup.palindrome.PalindromeCheckerApplication;
import com.cmegroup.palindrome.exception.InvalidRequestException;
import com.cmegroup.palindrome.service.PalindromeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PalindromeCheckerApplication.class)
@AutoConfigureMockMvc
public class PalindromeServiceRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private PalindromeService palindromeService;

    public final ConcurrentHashMap<String, Boolean> getPalindromeMap() {
        return new ConcurrentHashMap<>() {{
        }};
    }

    @Test
    public void testCheckIfcmeGroupisPalindrome() throws Exception {
        getPalindromeMap().forEach((key, value) -> palindromeService.checkRequestIsPalindrome(key));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/check-palindrome/{request}", "cmeGroup")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().equalsIgnoreCase("Request cmeGroup is not a Palindrome"));
    }

    @Test
    public void testCheckIfMadamisPalindrome() throws Exception {
        getPalindromeMap().forEach((key, value) -> palindromeService.checkRequestIsPalindrome(key));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/check-palindrome/{request}", "madam")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().equalsIgnoreCase("Request madam is a Palindrome"));
    }

    @Test
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void testCheckIfSpaceisPalindrome() throws Exception {
        getPalindromeMap().forEach((key, value) -> palindromeService.checkRequestIsPalindrome(key));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/check-palindrome/{request}", "    ")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }
}
