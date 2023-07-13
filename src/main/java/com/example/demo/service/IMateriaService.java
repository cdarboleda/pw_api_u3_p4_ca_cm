package com.example.demo.service;

import com.example.demo.repository.modelo.Materia;

public interface IMateriaService {
	
	public Materia consultarPorCodigo(String codigo);
	public void guardar(Materia materia);

}
