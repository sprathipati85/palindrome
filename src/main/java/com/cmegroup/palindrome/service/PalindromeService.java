package com.cmegroup.palindrome.service;

import com.cmegroup.palindrome.cache.MapLocalDataStore;
import com.cmegroup.palindrome.repository.PalindromeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * PalindromeService
 * checks if it a palindrome and writes data to datastore, caches the values.
 */

@Service
public class PalindromeService implements IPalindromeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PalindromeService.class);

    private final CacheManager cacheManager;
    private final PalindromeRepository palindromeRepository;
    private final MapLocalDataStore<String, Boolean> localDataStore;

    @Autowired
    public PalindromeService(MapLocalDataStore<String, Boolean> localDataStore, CacheManager cacheManager, PalindromeRepository palindromeRepository) {
        this.localDataStore = localDataStore;
        this.cacheManager = cacheManager;
        this.palindromeRepository = palindromeRepository;
    }

    @Override
    @Cacheable(cacheNames="requestCache")
    public Boolean checkRequestIsPalindrome(String request) {
        LOGGER.info("Check Request {} is Palindrome", request);

        if(localDataStore.getAll().size() > 0 && localDataStore.exists(request)) {
            return localDataStore.get(request);
        }

        Boolean palindrome = request.equals(new StringBuilder(request).reverse().toString());
        localDataStore.addOrUpdate(request, palindrome);
        palindromeRepository.save(request.concat(",").concat(palindrome.toString()));
        LOGGER.info("Request {} is {} palindrome ", request,palindrome);
        return palindrome;
    }
}

