package com.cmegroup.palindrome.repository;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

/**
 * PalindromeRepository for future implementation
 */

@Repository
public class PalindromeRepository implements IPalindromeRepository{

    private static final Logger LOGGER = LoggerFactory.getLogger(PalindromeRepository.class);

    @Override
    public Object save(Object entity) {
        try {
            File f = new File("src/main/resources/Palindrome.csv");
            Set<String> lines = new HashSet<>(FileUtils.readLines(f));
            lines.add(entity.toString());
            Files.write(f.toPath(), "".getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING);
            LOGGER.info("Palindrome doesnt exist in file, appending to file");
                lines.forEach(line -> {
                    try {
                        Files.write(f.toPath(), line.getBytes(), StandardOpenOption.APPEND);
                        Files.write(f.toPath(), System.lineSeparator().getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        LOGGER.error("Exception writing to file", e);
                    }
                });
        } catch (IOException ex) {
            LOGGER.error("Exception during file creation", ex);
        }
        return true;
    }
}
