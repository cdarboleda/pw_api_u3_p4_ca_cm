package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IMateriaRepository;
import com.example.demo.repository.modelo.Materia;
import com.example.demo.service.to.MateriaTO;

@Service
public class MateriaServiceImpl implements IMateriaService{

	@Autowired
	private IMateriaRepository materiaRepository;
	
	@Override
	public Materia consultarPorCodigo(String codigo) {
		// TODO Auto-generated method stub
		return this.materiaRepository.seleccionarPorCodigo(codigo);
	}

	@Override
	public void guardar(Materia materia) {
		// TODO Auto-generated method stub
		this.materiaRepository.insertar(materia);
	}

	@Override
	public List<Materia> buscarTodos() {
		// TODO Auto-generated method stub
		return this.materiaRepository.seleccionarTodos();
	}
	
	@Override
	public Materia buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.materiaRepository.buscarPorId(id);
	}

	@Override
	public void actualizar(Materia materia) {
		// TODO Auto-generated method stub
		this.materiaRepository.actualizar(materia);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.materiaRepository.borrar(id);
	}

	@Override
	public List<MateriaTO> buscarPorCedulaEstudiante(String cedula) {
		List<Materia> lista = this.materiaRepository.buscarPorCedulaEstudiante(cedula);
		List<MateriaTO> listaTO = lista.stream().map(materia -> this.convertir(materia)).collect(Collectors.toList());
		return listaTO;
	}
	
	@Override
	public MateriaTO buscarPorIdTO(Integer id) {
		return this.convertir(this.materiaRepository.buscarPorId(id));
	}
	
	
	private MateriaTO convertir(Materia materia) {
		MateriaTO mat = new MateriaTO();
		mat.setId(materia.getId());
		mat.setNombre(materia.getNombre());
		mat.setNumeroCreditos(materia.getNumeroCreditos());
		return mat;
	}

}
