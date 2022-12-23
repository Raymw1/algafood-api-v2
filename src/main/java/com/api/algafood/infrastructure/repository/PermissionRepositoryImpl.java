package com.api.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.api.algafood.domain.model.Permission;
import com.api.algafood.domain.repository.PermissionRepository;

@Component
public class PermissionRepositoryImpl implements PermissionRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Permission> findAll() {
		List<Permission> permissions = manager.createQuery("from Permission", Permission.class).getResultList();
		return permissions;
	}

	@Override
	public Permission findOne(Long id) {
		Permission permission = manager.find(Permission.class, id);
		return permission;
	}

	@Override
	@Transactional
	public Permission save(Permission permission) {
		permission = manager.merge(permission);
		return permission;
	}

	@Override
	@Transactional
	public void remove(Permission permission) {
		permission = this.findOne(permission.getId());
		manager.remove(permission);
	}
	
}
