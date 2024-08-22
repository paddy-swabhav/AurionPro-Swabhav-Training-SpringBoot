package com.techlabs.cashmgmt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.cashmgmt.entity.Client;
import com.techlabs.cashmgmt.entity.KycStatus;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

	Page<Client> findAllByCompanyName(String name, Pageable pageable);

	Page<Client> findAllByKycStatus(KycStatus status, Pageable pageable);

}
