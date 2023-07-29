package com.example.demo.controller;

//Importacion estática
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.IEstudianteService;
import com.example.demo.service.IMateriaService;
import com.example.demo.service.to.EstudianteTO;
import com.example.demo.service.to.MateriaTO;;

//Este es el servicio llamado estudiantes (clase controller)
//Con la capacidad de buscar (el metodo de la clase)=

@RestController
@RequestMapping("/estudiantes")
@CrossOrigin
public class EstudianteControllerRestFul {
	
	@Autowired
	private IEstudianteService estudianteService;
	@Autowired
	private IMateriaService materiaService;
	
	//Codigos de estado propios
	//GET
	@GetMapping(path="/{cedula}", produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Estudiante consultarPorCedula(@PathVariable String cedula) {
		//return ResponseEntity.status(227).body(this.estudianteService.consultarPorCedula(cedula));
		return this.estudianteService.consultarPorCedula(cedula);
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
	@PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
	public void guardar(@RequestBody Estudiante estudiante) {
		this.estudianteService.guardar(estudiante);
	}
	
	//Aqui el path no deberia ser un verbo, solo es didactico
	@PostMapping(path="/guardar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public Estudiante guardar2(@RequestBody Estudiante estudiante) {
		this.estudianteService.guardar(estudiante);
		return this.consultarPorCedula(estudiante.getCedula());
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
	
	@GetMapping(path="/hateoas", produces = MediaType.APPLICATION_JSON_VALUE)//path didactico, no se debe usar verbos
	public ResponseEntity<List<EstudianteTO>> consultarTodosHATEOAS(){
		List<EstudianteTO> lista = this.estudianteService.buscarTodosHATEOAS();
		//link de hypermedia para cada objeto
		for(EstudianteTO e: lista) {
			Link myLink = linkTo(methodOn(EstudianteControllerRestFul.class)
					.buscarPorEstudiante(e.getCedula()))
					.withRel("materias");
			
			e.add(myLink);
			
		}
		return new ResponseEntity<>(lista, null, 200);
	}
	
	@GetMapping(path="/{cedula}/materias", produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MateriaTO>> buscarPorEstudiante(@PathVariable String cedula){
		List<MateriaTO> lista = this.materiaService.buscarPorCedulaEstudiante(cedula);
		for(MateriaTO m: lista) {
			Link myLink = linkTo(methodOn(MateriaControllerRestFul.class)
					.consultarPorIdTO(m.getId()))
					.withSelfRel();
			
			m.add(myLink);
		}
		
		return new ResponseEntity<>(lista, null, 200);
	}
	


}
