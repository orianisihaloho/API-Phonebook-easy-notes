package com.example.phonebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.phonebook.model.PhoneBook;

@Repository
public interface PhoneBookRepository extends JpaRepository<PhoneBook, Long> {

}