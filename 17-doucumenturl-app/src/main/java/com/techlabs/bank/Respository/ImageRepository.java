package com.techlabs.bank.Respository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.bank.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>{

}
