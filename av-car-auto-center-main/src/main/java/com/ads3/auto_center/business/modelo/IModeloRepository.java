package com.ads3.auto_center.business.modelo;

import com.ads3.auto_center.core.repositories.IGenericRepository;

public interface IModeloRepository extends IGenericRepository<ModeloModel> {
    boolean existsByNomeModeloIgnoreCaseAndMarca_Id(String nomeModelo, Long idMarca);
}