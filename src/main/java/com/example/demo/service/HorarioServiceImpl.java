package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IHorarioRepository;
import com.example.demo.repository.modelo.Horario;

@Service
public class HorarioServiceImpl implements IHorarioService {

	@Autowired
	private IHorarioRepository horarioRepository;
	
	@Override
	public List<Horario> consultarPorDia(String dia) {
		// TODO Auto-generated method stub
		return this.horarioRepository.seleccionarPorDia(dia);
	}

	@Override
	public void guardar(Horario horario) {
		// TODO Auto-generated method stub
		this.horarioRepository.insertar(horario);
	}

	@Override
	public void actualizar(Horario horario) {
		// TODO Auto-generated method stub
		this.horarioRepository.actualizar(horario);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.horarioRepository.borrar(id);
	}

	@Override
	public Horario buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.horarioRepository.buscarPorId(id);
	}

}
