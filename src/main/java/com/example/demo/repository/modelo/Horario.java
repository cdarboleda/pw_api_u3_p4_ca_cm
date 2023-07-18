package com.example.demo.repository.modelo;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="horario")
public class Horario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_horario")
	@SequenceGenerator(name="seq_horario", sequenceName = "seq_horario", allocationSize = 1)
	@Column(name = "hora_id")
	private Integer id;
	@Column(name = "hora_materia")
	private String materia;
	@Column(name = "hora_dia")
	private String dia;
	@Column(name = "hora_hora_inicio")
	private LocalTime horaInicio;
	@Column(name = "hora_horas")
	private Integer horas;
	
	//SET Y GET
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public LocalTime getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Integer getHoras() {
		return horas;
	}
	public void setHoras(Integer horas) {
		this.horas = horas;
	}
	@Override
	public String toString() {
		return "Horario [id=" + id + ", materia=" + materia + ", dia=" + dia + ", horaInicio=" + horaInicio + ", horas="
				+ horas + "]";
	}
	
	
	

}
