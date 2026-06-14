package com.ads3.auto_center.core.services;

import com.ads3.auto_center.core.domains.BaseModel;
import com.ads3.auto_center.core.repositories.IGenericRepository;
import com.ads3.auto_center.core.validations.IGenericValidation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGenericService<E extends BaseModel, R extends IGenericRepository<E>, V extends IGenericValidation<E, R>> {
    E findByIdActive(Long id);
    Page<E> findAllActive(Pageable pageable);
    E insert(E entity);
    E update(E entity);
    void delete(Long id);
}