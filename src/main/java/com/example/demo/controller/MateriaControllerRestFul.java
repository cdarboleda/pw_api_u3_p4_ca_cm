package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Materia;
import com.example.demo.service.IMateriaService;

@RestController
@RequestMapping("/materias")
public class MateriaControllerRestFul {

	@Autowired
	private IMateriaService materiaService;
	
	//GET
	@GetMapping(path="/{codigo}")
	public Materia consultarPorCodigo(@PathVariable String codigo) {
		return this.materiaService.consultarPorCodigo(codigo);
	}
	
	@GetMapping(path="/buscarTodos")
	public List<Materia> buscarTodos(){
		//buscarTodos?provincia=pichincha
		return this.materiaService.buscarTodos();
	}
	
	//POST
	@PostMapping(path="/guardar")
	public void guardar(@RequestBody Materia materia) {
		this.materiaService.guardar(materia);
	}
	
	//PUT
	@PutMapping(path="/actualizar/{identificador}")
	public void actualizar(@RequestBody Materia materia, @PathVariable Integer identificador) {
		materia.setId(identificador);
		this.materiaService.actualizar(materia);
	}
	
	//PATCH
	@PatchMapping(path="/actualizarParcial/{identificador}")
	public void actualizarParcial(@RequestBody Materia materia,
			@PathVariable Integer identificador) {
		materia.setId(identificador);
		Materia mate1 = this.materiaService.buscarPorId(identificador);
		mate1.setCodigo(materia.getCodigo());//solo le paso el codigo
		this.materiaService.actualizar(mate1);
	}
	
	//DELETE
	@DeleteMapping(path="/borrar/{id}")
	public void borrar(@PathVariable Integer id) {
		this.materiaService.eliminar(id);
	}
}
