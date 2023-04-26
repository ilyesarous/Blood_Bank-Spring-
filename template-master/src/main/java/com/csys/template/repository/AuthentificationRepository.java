package com.csys.template.repository;

import com.csys.template.domain.Authentification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AuthentificationRepository extends JpaRepository<Authentification, String> {
    Authentification findByAddressAndCode(String address, String code);
    Authentification findByAddress(String address);
}
