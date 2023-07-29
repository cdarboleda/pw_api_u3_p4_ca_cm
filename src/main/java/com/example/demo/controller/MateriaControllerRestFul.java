package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.example.demo.service.to.MateriaTO;

@RestController
@RequestMapping("/materias")
public class MateriaControllerRestFul {

	@Autowired
	private IMateriaService materiaService;

	/*
	@GetMapping(path = "/{codigo}")
	public ResponseEntity<Materia> consultarPorCodigo(@PathVariable String codigo) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.materiaService.consultarPorCodigo(codigo));
	}
	*/

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MateriaTO> consultarPorIdTO(@PathVariable Integer id) {
		MateriaTO materia = this.materiaService.buscarPorIdTO(id); // Link
		//Link myLink = linkTo(methodOn(MateriaControllerRestFul.class).consultarPorIdTO(id)).withRel("materias");
		//materia.add(myLink);
		return new ResponseEntity<>(materia, null, 200);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Materia> buscarTodos() {
		// buscarTodos?provincia=pichincha
		return this.materiaService.buscarTodos();
	}

	// POST
	@PostMapping(path = "/guardar")
	public void guardar(@RequestBody Materia materia) {
		this.materiaService.guardar(materia);
	}

	// PUT
	@PutMapping(path = "/actualizar/{identificador}")
	public void actualizar(@RequestBody Materia materia, @PathVariable Integer identificador) {
		materia.setId(identificador);
		this.materiaService.actualizar(materia);
	}

	// PATCH
	@PatchMapping(path = "/actualizarParcial/{identificador}")
	public void actualizarParcial(@RequestBody Materia materia, @PathVariable Integer identificador) {
		materia.setId(identificador);
		Materia mate1 = this.materiaService.buscarPorId(identificador);
		mate1.setNombre(materia.getNombre());// solo le paso el nombre
		this.materiaService.actualizar(mate1);
	}

	// DELETE
	@DeleteMapping(path = "/borrar/{id}")
	public void borrar(@PathVariable Integer id) {
		this.materiaService.eliminar(id);
	}
}
