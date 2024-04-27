package com.mx.sda.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mx.sda.dto.GuardarUsuario;
import com.mx.sda.exception.InvalidPasswordException;
import com.mx.sda.persistencia.entity.Usuario;
import com.mx.sda.persistencia.repository.UsuarioRepository;
import com.mx.sda.service.UsuarioService;

import com.mx.sda.util.Roles;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Usuario registerOneUser(GuardarUsuario nuevoUsuario) {
		validatePassword(nuevoUsuario);
		
		Usuario usuario = new Usuario();
		usuario.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));
		usuario.setUsername(nuevoUsuario.getUsername());
		usuario.setNombre(nuevoUsuario.getNombre());
		usuario.setRoles(Roles.ROL_USUARIO);
		
		return usuarioRepository.save(usuario);
	}
	
	@Override
	public Optional<Usuario> findOneByUsername(String username){
		return usuarioRepository.findByUsername(username);
	}
	
	private void validatePassword(GuardarUsuario dto) {
		
		if(!StringUtils.hasText(dto.getPassword()) || !StringUtils.hasText(dto.getRepitePassword())) {
			throw new InvalidPasswordException("Password o rpeatPassword vacios");
		}
		
		if(!dto.getPassword().equals(dto.getRepitePassword())) {
			throw new InvalidPasswordException("El password no coincide");
		}
	}

}
