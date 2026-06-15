package com.ads3.auto_center.business.itemServicoInterno;

import com.ads3.auto_center.business.ordemDeServico.OrdemDeServicoModel;
import com.ads3.auto_center.business.servicoInterno.ServicoInternoModel;
import com.ads3.auto_center.core.helpers.IGenericMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class ItemServicoInternoMapper implements IGenericMapper<ItemServicoInternoModel, ItemServicoInternoDTO> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public ItemServicoInternoDTO toDto(ItemServicoInternoModel entity) {
        if (entity == null) return null;
        ItemServicoInternoDTO dto = new ItemServicoInternoDTO();
        dto.setId(entity.getId());
        dto.setActive(entity.isAtivo());
        dto.setQuantidade(entity.getQuantidade());
        dto.setValorUnitario(entity.getValorUnitario());
        dto.setDesconto(entity.getDesconto());
        if (entity.getOrdemDeServico() != null) dto.setIdOs(entity.getOrdemDeServico().getId());
        if (entity.getServicoInterno() != null) {
            dto.setIdSrvInterno(entity.getServicoInterno().getId());
            dto.setNomeServico(entity.getServicoInterno().getNomeServico());
        }
        return dto;
    }

    @Override
    public ItemServicoInternoModel toEntity(ItemServicoInternoDTO dto) {
        if (dto == null) return null;
        ItemServicoInternoModel entity = new ItemServicoInternoModel();
        entity.setId(dto.getId());
        entity.setAtivo(dto.isActive());
        entity.setQuantidade(dto.getQuantidade());
        entity.setValorUnitario(dto.getValorUnitario());
        entity.setDesconto(dto.getDesconto());
        if (dto.getIdOs() != null) {
            entity.setOrdemDeServico(em.getReference(OrdemDeServicoModel.class, dto.getIdOs()));
        }
        if (dto.getIdSrvInterno() != null) {
            entity.setServicoInterno(em.getReference(ServicoInternoModel.class, dto.getIdSrvInterno()));
        }
        return entity;
    }
}