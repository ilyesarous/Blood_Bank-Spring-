package com.csys.template.repository;

import com.csys.template.domain.Authentification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AuthentificationRepository extends JpaRepository<Authentification, String> {
//    @Query("SELECT u FROM Authentification  WHERE u.address=:address")
    Authentification findByAddress(String address);
    Authentification findBycode(String code);
}
