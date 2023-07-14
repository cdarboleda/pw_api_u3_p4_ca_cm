package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
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

	@Override
	public void insertar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.entityManager.persist(estudiante);
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.entityManager.merge(estudiante);
	}

	@Override
	public void actualizarParcial(String cedulaActual, String cedulaNueva) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Estudiante e SET e.cedula = :datoCedula WHERE e.cedula = :datoCondicion ";
		Query myQuery = this.entityManager.createQuery(sql);
		myQuery.setParameter("datoCondicion", cedulaActual);
		myQuery.setParameter("datoCedula", cedulaNueva);
		myQuery.executeUpdate();
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.buscarPorId(id));
	}

	@Override
	public Estudiante buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Estudiante.class, id);
	}

	@Override
	public List<Estudiante> seleccionarTodos() {
		// TODO Auto-generated method stub
        TypedQuery<Estudiante> myQuery = this.entityManager.createQuery("SELECT e FROM Estudiante e", Estudiante.class);
        return myQuery.getResultList();
	}
	
	

}
