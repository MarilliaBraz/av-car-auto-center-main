package com.ads3.auto_center.business.ordemDeServico;

import com.ads3.auto_center.core.repositories.IGenericRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface IOrdemDeServicoRepository extends IGenericRepository<OrdemDeServicoModel> {

    List<OrdemDeServicoModel> findByVeiculo_Id(Long idVeiculo);

    @Query(value = "SELECT COALESCE(MAX(sequencial), 0) FROM ordens_de_servico " +
                   "WHERE EXTRACT(YEAR FROM data_abertura) = EXTRACT(YEAR FROM CURRENT_DATE)",
           nativeQuery = true)
    long findUltimoSequencialOS();
}