package com.cmegroup.palindrome.service;

import com.cmegroup.palindrome.PalindromeCheckerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = PalindromeCheckerApplication.class)
public class PalindromeServiceTest {

    @Autowired
    private PalindromeService palindromeService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testCheckPalindrome() {
        Boolean isPalindrome = palindromeService.checkRequestIsPalindrome("cmeGroup");
        assertFalse(isPalindrome);
    }

    @Test
    public void testValidateCache() {
        Boolean isPalindrome = palindromeService.checkRequestIsPalindrome("madam");
        assertTrue(isPalindrome);
        assertEquals(cacheManager.getCacheNames().size(), 1);
        assertEquals(cacheManager.getCacheNames().stream().findFirst().get(), "requestCache");
        assertEquals(cacheManager.getCache("requestCache").get("cmeGroup").get(), Boolean.FALSE);
        assertEquals(cacheManager.getCache("requestCache").get("madam").get(), Boolean.TRUE);
    }
}