package com.cmegroup.palindrome.repository;

public interface IPalindromeRepository<T> {
    <S extends T> S save(S entity);
}