package com.ads3.auto_center.business.colaborador;

import com.ads3.auto_center.core.repositories.IGenericRepository;

public interface IColaboradorRepository extends IGenericRepository<ColaboradorModel> {
    boolean existsByCpf(String cpf);
}