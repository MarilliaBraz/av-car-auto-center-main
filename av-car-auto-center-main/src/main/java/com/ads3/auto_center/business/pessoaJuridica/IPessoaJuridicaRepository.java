package com.ads3.auto_center.business.pessoaJuridica;

import com.ads3.auto_center.core.repositories.IGenericRepository;
import java.util.Optional;

public interface IPessoaJuridicaRepository extends IGenericRepository<PessoaJuridicaModel> {
    boolean existsByCnpj(String cnpj);
    Optional<PessoaJuridicaModel> findByCnpj(String cnpj);
}