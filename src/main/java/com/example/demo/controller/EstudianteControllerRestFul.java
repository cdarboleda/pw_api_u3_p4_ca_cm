package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.IEstudianteService;

//Este es el servicio llamado estudiantes (clase controller)
//Con la capacidad de buscar (el metodo de la clase)=

@RestController
@RequestMapping("/estudiantes")
public class EstudianteControllerRestFul {
	
	@Autowired
	private IEstudianteService estudianteService;
	
	//GET
	@GetMapping(path="/buscar")
	public Estudiante consultarPorCedula() {
		String cedula = "1234567890";
		return this.estudianteService.consultarPorCedula(cedula);
	}
	
	//POST
	@PostMapping(path="/guardar")
	public void guardar(@RequestBody Estudiante estudiante) {
		this.estudianteService.guardar(estudiante);
	}
	
	//PUT
	@PutMapping(path="/actualizar")
	public void actualizar() {
		
	}
	
	//PATCH
	@PutMapping(path="/actualizarParcial")
	public void actualizarParcial() {
		
	}
	
	//DELETE
	@DeleteMapping(path="/borrar")
	public void borrar() {
		
	}

}
