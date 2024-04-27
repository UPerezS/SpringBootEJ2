package com.mx.sda.service;

import java.util.Optional;

import com.mx.sda.dto.GuardarUsuario;
import com.mx.sda.persistencia.entity.Usuario;

public interface UsuarioService {
	
	Usuario registerOneUser(GuardarUsuario newUser);
	
	Optional<Usuario> findOneByUsername(String username);

}
