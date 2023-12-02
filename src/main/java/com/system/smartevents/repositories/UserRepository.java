package com.system.smartevents.repositories;

import com.system.smartevents.models.AgenciaModel;
import com.system.smartevents.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UsuarioModel, String> {

    UserDetails findByLogin(String login);

}
