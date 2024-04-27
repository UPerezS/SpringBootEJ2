package com.mx.sda.util;

import java.util.Arrays;
import java.util.List;

public enum Roles {

	ROL_ADMINISTRATOR(Arrays.asList(PermisosRol.READ_ALL_GRUPOS, PermisosRol.READ_ONE_GRUPOS, 
			PermisosRol.CREATE_ONE_GRUPOS, PermisosRol.UPDATE_ONE_GRUPOS,
			PermisosRol.DISABLE_ONE_GRUPOS, PermisosRol.READ_ALL_ALUMNOS,
			PermisosRol.READ_ONE_ALUMNOS, PermisosRol.CREATE_ONE_ALUMNOS,
			PermisosRol.UPDATE_ONE_ALUMNOS, PermisosRol.DISABLE_ONE_ALUMNOS
			)), ROL_USUARIO(Arrays.asList(PermisosRol.READ_ALL_DOCENTES, PermisosRol.READ_ONE_DOCENTES,
					PermisosRol.CREATE_ONE_DOCENTES,
					PermisosRol.UPDATE_ONE_DOCENTES,
					PermisosRol.DISABLE_ONE_DOCENTES));
	
	private List<PermisosRol> permisos;
	
	Roles(List<PermisosRol> permisos){
		this.permisos = permisos;
	}
	
	public List<PermisosRol> getPermisos(){
		return permisos;
	}
	
	public void setPermisos(List<PermisosRol> permisos) {
		this.permisos = permisos;
	}
	
}
