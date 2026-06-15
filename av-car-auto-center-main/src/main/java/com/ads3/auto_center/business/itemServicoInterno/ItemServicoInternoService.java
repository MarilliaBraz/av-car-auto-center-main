package com.ads3.auto_center.business.itemServicoInterno;

import com.ads3.auto_center.core.services.GenericService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServicoInternoService extends GenericService<ItemServicoInternoModel, IItemServicoInternoRepository, ItemServicoInternoValidacao> {

    @Transactional(readOnly = true)
    public List<ItemServicoInternoModel> listarPorOs(Long idOs) {
        return repository.findItensAtivosByOs(idOs);
    }
}