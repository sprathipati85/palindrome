package com.cmegroup.palindrome.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ValidatorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorService.class);

    public boolean validate(String request) {
        if(StringUtils.containsWhitespace(request)) {
            return false;
        }
        return  true;
    }
}
