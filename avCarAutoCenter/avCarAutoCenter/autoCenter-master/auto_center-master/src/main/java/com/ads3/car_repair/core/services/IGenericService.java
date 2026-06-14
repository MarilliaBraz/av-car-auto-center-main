package com.ads3.car_repair.core.services;

import com.ads3.car_repair.core.domains.BaseModel;
import com.ads3.car_repair.core.repositories.IGenericRepository;
import com.ads3.car_repair.core.validations.IGenericValidation;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IGenericService<E extends BaseModel, R extends IGenericRepository<E>, V extends IGenericValidation<E, R>> {

	E findByIdActive(UUID id);

	Page<E> findAllActive(Pageable pageable);

	E insert(E entity);
	E update(E entity);

	void delete(UUID id);

}
