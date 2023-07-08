package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EstudianteRepositoryImpl implements IEstudianteRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Estudiante seleccionarPorCedula(String cedula) {
		// TODO Auto-generated method stub
		String sql = "SELECT e FROM Estudiante e WHERE e.cedula = :datoCedula";
		TypedQuery<Estudiante> myQuery = this.entityManager.createQuery(sql, Estudiante.class);
		myQuery.setParameter("datoCedula", cedula);
		
		return myQuery.getSingleResult();
	}
	
	

}
