package com.ads3.auto_center.business.marca;

import com.ads3.auto_center.core.repositories.IGenericRepository;

public interface IMarcaRepository extends IGenericRepository<MarcaModel> {
    boolean existsByNomeMarcaIgnoreCase(String nomeMarca);
}