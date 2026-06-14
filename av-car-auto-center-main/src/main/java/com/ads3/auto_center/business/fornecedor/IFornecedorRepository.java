package com.ads3.auto_center.business.fornecedor;

import com.ads3.auto_center.core.repositories.IGenericRepository;

public interface IFornecedorRepository extends IGenericRepository<FornecedorModel> {
    boolean existsByCnpj(String cnpj);
}