package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	//Codigos de estado propios
	//GET
	@GetMapping(path="/{cedula}")
	public ResponseEntity<Estudiante> consultarPorCedula(@PathVariable String cedula) {
		return ResponseEntity.status(227).body(this.estudianteService.consultarPorCedula(cedula));
	}
	
	@GetMapping(path="/status/{cedula}")
	public ResponseEntity<Estudiante> consultarPorCedulaStatus(@PathVariable String cedula) {
		return ResponseEntity.status(227).body(this.estudianteService.consultarPorCedula(cedula));
	}
	
	//Codigos de estado propios con mensaje
	@GetMapping
	public ResponseEntity<List<Estudiante>> buscarTodos(){
		//buscarTodos?provincia=pichincha;
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("detalleMensaje", "Ciudadano consultados exitosamente");//clave - valor
		cabeceras.add("valorAPI", "Incalculable");//clave - valor
		return new ResponseEntity<>(this.estudianteService.buscarTodos(), cabeceras, 228);
	}
	
	//POST
	//No hace falta identificador ya que un POST siempre crea un solo recurso no varios
	@PostMapping
	public void guardar(@RequestBody Estudiante estudiante) {
		this.estudianteService.guardar(estudiante);
	}
	
	//PUT
	@PutMapping(path="/{identificador}")
	public void actualizar(@RequestBody Estudiante estudiante, @PathVariable Integer identificador) {
		estudiante.setId(identificador);
		this.estudianteService.actualizar(estudiante);
	}
	
	//PATCH
	@PatchMapping(path="/{identificador}")
	public void actualizarParcial(@RequestBody Estudiante estudiante, @PathVariable String identificador) {
		Estudiante estudianteActual = this.estudianteService.consultarPorCedula(identificador);
		this.estudianteService.actualizarParcial(estudianteActual.getCedula(), estudiante.getCedula());
	}
	
	//DELETE
	@DeleteMapping(path="/{id}")
	public void borrar(@PathVariable Integer id) {
		this.estudianteService.eliminar(id);
	}
	


}
