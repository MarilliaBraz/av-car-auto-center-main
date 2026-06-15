package com.ads3.auto_center.business.itemServicoInterno;

import com.ads3.auto_center.core.repositories.IGenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IItemServicoInternoRepository extends IGenericRepository<ItemServicoInternoModel> {

    @Query("SELECT i FROM ItemServicoInternoModel i JOIN FETCH i.servicoInterno WHERE i.ordemDeServico.id = :idOs AND i.ativo = true")
    List<ItemServicoInternoModel> findItensAtivosByOs(@Param("idOs") Long idOs);
}