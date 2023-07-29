package com.example.demo.service;

import java.util.List;

import com.example.demo.repository.modelo.Materia;
import com.example.demo.service.to.MateriaTO;

public interface IMateriaService {
	
	public Materia buscarPorId(Integer id);
	public void guardar(Materia materia);
	public List<Materia> buscarTodos();
	public void actualizar(Materia materia);
	public void eliminar(Integer id);
	public List<MateriaTO> buscarPorCedulaEstudiante(String cedula);
	public MateriaTO buscarPorIdTO(Integer id);

}
