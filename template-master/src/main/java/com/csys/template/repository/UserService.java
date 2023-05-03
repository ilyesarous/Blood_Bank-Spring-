package com.csys.template.repository;

import com.csys.template.dto.AuthentificationDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
        ResponseEntity<?> saveUser(AuthentificationDTO user);
}
