package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.modelo.Horario;

@Transactional
@Repository
public class HorarioRepositoryImpl implements IHorarioRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Horario> seleccionarPorDia(String dia) {
		// TODO Auto-generated method stub
		String sql = "SELECT h FROM Horario h WHERE h.dia = :dia";
		TypedQuery<Horario> myQuery = this.entityManager.createQuery(sql, Horario.class);
		myQuery.setParameter("dia", dia);
		return myQuery.getResultList();
	}

	@Override
	public void insertar(Horario horario) {
		// TODO Auto-generated method stub
		this.entityManager.persist(horario);
	}

	@Override
	public void actualizar(Horario horario) {
		// TODO Auto-generated method stub
		this.entityManager.merge(horario);
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.buscarPorId(id));
	}

	@Override
	public Horario buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Horario.class, id);
	}

}
