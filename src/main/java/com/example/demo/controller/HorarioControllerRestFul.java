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

import com.example.demo.repository.modelo.Horario;
import com.example.demo.service.IHorarioService;

@RestController
@RequestMapping("/horarios")
public class HorarioControllerRestFul {
	
	@Autowired
	private IHorarioService horarioService;
	
	//GET
	@GetMapping(path="/{dia}")
	public List<Horario> consultarPorDia(@PathVariable String dia) {
		return this.horarioService.consultarPorDia(dia);
	}
	
	//POST
	@PostMapping
	public void guardar(@RequestBody Horario horario) {
		this.horarioService.guardar(horario);
	}
	
	//PUT
	@PutMapping(path="/{id}")
	public void actualizar(@RequestBody Horario horario, @PathVariable Integer id) {
		horario.setId(id);
		this.horarioService.actualizar(horario);
	}
	
	//PATCH
	@PatchMapping(path="/{id}")
	public void actualizarParcial(@RequestBody Horario horario, @PathVariable Integer id) {
		horario.setId(id);
		Horario hor1 = this.horarioService.buscarPorId(id);
		hor1.setDia(horario.getDia());
		this.horarioService.actualizar(hor1);
	}

	//DELETE
	@DeleteMapping(path="/{id}")
	public void eliminar(@PathVariable Integer id) {
		this.horarioService.eliminar(id);
	}
}
