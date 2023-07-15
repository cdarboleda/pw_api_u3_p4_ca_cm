package com.example.demo.repository;

import java.util.List;

import com.example.demo.repository.modelo.Materia;

public interface IMateriaRepository {
	
	public Materia seleccionarPorCodigo(String codigo);
	public void insertar(Materia materia);
	public List<Materia> seleccionarTodos();
	public void actualizar(Materia materia);
	public void borrar(Integer id);
	public Materia buscarPorId(Integer id);
	public void actualizarParcial(String codigoActual, String codigoNuevo);

}
