package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Materia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public class MateriaRepositoryImpl implements IMateriaRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Materia seleccionarPorCodigo(String codigo) {
		// TODO Auto-generated method stub
		String sql = "SELECT m FROM Materia m WHERE m.codigo = :codigo";
		TypedQuery<Materia> myQuery = this.entityManager.createQuery(sql, Materia.class);
		myQuery.setParameter("codigo", codigo);
		return myQuery.getSingleResult();
	}

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
	public void actualizarParcial(String codigoActual, String codigoNuevo) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Materia m SET m.codigo = :datoCodigo WHERE m.codigo = :datoCondicion";
		Query myQuery = this.entityManager.createQuery(sql);
		myQuery.setParameter("datoCondicion", codigoActual);
		myQuery.setParameter("datoCodigo", codigoNuevo);
		myQuery.executeUpdate();
		
	}

}
