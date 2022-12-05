package com.cmegroup.palindrome.rest;

import com.cmegroup.palindrome.exception.InvalidRequestException;
import com.cmegroup.palindrome.service.IPalindromeService;
import com.cmegroup.palindrome.service.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The PalindromeCheckerController, exposes 1 endpoint.
 * GET checkRequestIsPalindrome by request
 */

@RestController
@RequestMapping("/api")
public class PalindromeCheckerController {
    private IPalindromeService palindromeService;
    private ValidatorService validatorService;

    @Autowired
    public PalindromeCheckerController(IPalindromeService palindromeService, ValidatorService validatorService) {
        this.palindromeService = palindromeService;
        this.validatorService = validatorService;
    }

    @GetMapping(value = "/check-palindrome/{request}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> checkRequestIsPalindrome(@PathVariable String request) {
        if(!validatorService.validate(request)) {
            throw new InvalidRequestException(request +" is a bad request and rejected");
        } else {
            try {
                boolean isPalindrome = palindromeService.checkRequestIsPalindrome(request);
                return new ResponseEntity<>(
                        "Request " + request + " is " + (isPalindrome ? "" : "not") + " a Palindrome", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<String>("Error processing request", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
