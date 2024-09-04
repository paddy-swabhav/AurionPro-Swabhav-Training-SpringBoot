package com.techlabs.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.bank.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer>{

}
