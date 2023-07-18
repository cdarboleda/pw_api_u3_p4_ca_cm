package com.example.demo.repository;

import java.util.List;

import com.example.demo.repository.modelo.Horario;

public interface IHorarioRepository {
	
	public List<Horario> seleccionarPorDia(String dia);
	public void insertar(Horario horario);
	public void actualizar(Horario horario);
	public void borrar(Integer id);
	public Horario buscarPorId(Integer id);
	

}
