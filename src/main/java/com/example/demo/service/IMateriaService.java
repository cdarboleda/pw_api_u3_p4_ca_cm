package com.example.demo.service;

import java.util.List;

import com.example.demo.repository.modelo.Materia;

public interface IMateriaService {
	
	public Materia consultarPorCodigo(String codigo);
	public void guardar(Materia materia);
	public List<Materia> buscarTodos();
	public void actualizar(Materia materia);
	public void eliminar(Integer id);
	Materia buscarPorId(Integer id);

}
