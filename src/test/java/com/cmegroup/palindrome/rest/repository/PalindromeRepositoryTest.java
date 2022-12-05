package com.cmegroup.palindrome.rest.repository;

import com.cmegroup.palindrome.PalindromeCheckerApplication;
import com.cmegroup.palindrome.repository.PalindromeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = PalindromeCheckerApplication.class)
public class PalindromeRepositoryTest {

    @Autowired
    private PalindromeRepository palindromeRepository;

    @Test
    public void testCheckPalindrome() throws IOException {
        palindromeRepository.save("cmeGroup,false");
        File f = new File("src/main/resources/Palindrome.csv");
        assertTrue(f.exists());
    }
}