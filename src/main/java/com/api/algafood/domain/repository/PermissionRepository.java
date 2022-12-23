package com.api.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.api.algafood.domain.model.Permission;

@Repository
public interface PermissionRepository {
	List<Permission> findAll();
	Permission findOne(Long id);
	Permission save(Permission permission);
	void remove(Permission permission);
}
