package com.system.smartevents.services;

import com.system.smartevents.models.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.smartevents.repositories.UserRepository;

import java.util.List;

@Service
public class DadosUserService {
		private final UserRepository userRepository;
		
		
		 	@Autowired
		    public DadosUserService(UserRepository dadosRepository) {
		        this.userRepository = dadosRepository;
		    }

		    public List<UsuarioModel> obterDados() {
		        return userRepository.findAll();
		    }

}
