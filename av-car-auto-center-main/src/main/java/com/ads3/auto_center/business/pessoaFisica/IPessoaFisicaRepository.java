package com.ads3.auto_center.business.pessoaFisica;

import com.ads3.auto_center.core.repositories.IGenericRepository;
import java.util.Optional;

public interface IPessoaFisicaRepository extends IGenericRepository<PessoaFisicaModel> {
    boolean existsByCpf(String cpf);
    Optional<PessoaFisicaModel> findByCpf(String cpf);
}