package com.mx.sda.service.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mx.sda.dto.GuardarUsuario;
import com.mx.sda.dto.auth.LoginRequest;
import com.mx.sda.dto.auth.LoginResponse;
import com.mx.sda.dto.UsuarioRegistrado;
import com.mx.sda.persistencia.entity.Usuario;
import com.mx.sda.service.UsuarioService;

@Service
public class LoginService {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public UsuarioRegistrado registerOneUsuario(GuardarUsuario nuevoUsuario) {
		Usuario usuario = usuarioService.registerOneUser(nuevoUsuario);
		
		UsuarioRegistrado userDto = new UsuarioRegistrado();
		userDto.setId(usuario.getId());
		userDto.setName(usuario.getNombre());
		userDto.setUsername(usuario.getUsername());
		userDto.setRole(usuario.getRoles().name());
		
		String jwt = jwtService.generateToken(usuario, generateExtraClaims(usuario));
		userDto.setJwt(jwt);
		
		return userDto;
	}
	
	private Map<String, Object> generateExtraClaims(Usuario usuario){
		Map<String, Object> extraClaims = new HashMap<>();
		extraClaims.put("name", usuario.getNombre());
		extraClaims.put("role", usuario.getRoles());
		extraClaims.put("authorities", usuario.getAuthorities());
		
		return extraClaims;
	}
	
	public LoginResponse login(LoginRequest authRequest) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
				authRequest.getPassword());
		
		authenticationManager.authenticate(authentication);
		
		UserDetails usuario = usuarioService.findOneByUsername(authRequest.getUsername()).get();
		String jwt = jwtService.generateToken(usuario, generateExtraClaims((Usuario) usuario));
		
		LoginResponse authRsp = new LoginResponse();
		authRsp.setJwt(jwt);
		
		return authRsp;
	}
	
	public boolean validateToken(String jwt) {
		try {
			jwtService.extractUsername(jwt);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
