package com.ads3.auto_center.core.validations;

import com.ads3.auto_center.core.domains.BaseModel;
import com.ads3.auto_center.core.repositories.IGenericRepository;

public interface IGenericValidation<E extends BaseModel, R extends IGenericRepository<E>> {

    void validateFields(E entity);

    default void validateFieldsInsert(E entity) {}

    default void validateFieldsUpdate(E entity) {}

    default void validateInsert(E entity) {}

    default void validateUpdate(E entity) {}

    default void validateDelete(Long id) {}
}