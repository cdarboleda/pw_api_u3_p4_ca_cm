package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.modelo.Materia;

@Transactional
@Repository
public class MateriaRepositoryImpl implements IMateriaRepository{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Materia materia) {
		// TODO Auto-generated method stub
		this.entityManager.persist(materia);
	}

	@Override
	public List<Materia> seleccionarTodos() {
		// TODO Auto-generated method stub
		String sql = "SELECT m FROM Materia m";
		TypedQuery<Materia> myQuery = this.entityManager.createQuery(sql, Materia.class);
		return myQuery.getResultList();
	}

	@Override
	public void actualizar(Materia materia) {
		// TODO Auto-generated method stub
		this.entityManager.merge(materia);
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.buscarPorId(id));
	}

	@Override
	public Materia buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Materia.class, id);
	}

	@Override
	public void actualizarParcial(Integer id, String nuevoNombre) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Materia m SET m.nombre = :nuevoNombre WHERE m.id = :id";
		Query myQuery = this.entityManager.createQuery(sql);
		myQuery.setParameter("id", id);
		myQuery.setParameter("nuevoNombre", nuevoNombre);
		myQuery.executeUpdate();
		
	}

	@Override
	public List<Materia> buscarPorCedulaEstudiante(String cedula) {
		// TODO Auto-generated method stub
		String sql = "SELECT m FROM Materia m WHERE m.estudiante.cedula = :cedula";
		TypedQuery<Materia> myQuery = this.entityManager.createQuery(sql, Materia.class);
		myQuery.setParameter("cedula", cedula);
		return myQuery.getResultList();
	}

}
