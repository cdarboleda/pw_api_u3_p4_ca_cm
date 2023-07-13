package com.example.demo.repository;

import com.example.demo.repository.modelo.Materia;

public interface IMateriaRepository {
	
	public Materia seleccionarPorCodigo(String codigo);
	public void insertar(Materia materia);

}
