package com.ads3.auto_center.business.itemServicoExterno;

import com.ads3.auto_center.business.ordemDeServico.OrdemDeServicoModel;
import com.ads3.auto_center.business.servicoExterno.ServicoExternoModel;
import com.ads3.auto_center.core.helpers.IGenericMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class ItemServicoExternoMapper implements IGenericMapper<ItemServicoExternoModel, ItemServicoExternoDTO> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public ItemServicoExternoDTO toDto(ItemServicoExternoModel entity) {
        if (entity == null) return null;
        ItemServicoExternoDTO dto = new ItemServicoExternoDTO();
        dto.setId(entity.getId());
        dto.setActive(entity.isAtivo());
        dto.setQuantidade(entity.getQuantidade());
        dto.setValorUnitario(entity.getValorUnitario());
        dto.setDesconto(entity.getDesconto());
        if (entity.getOrdemDeServico() != null) dto.setIdOs(entity.getOrdemDeServico().getId());
        if (entity.getServicoExterno() != null) dto.setIdSrvExterno(entity.getServicoExterno().getId());
        return dto;
    }

    @Override
    public ItemServicoExternoModel toEntity(ItemServicoExternoDTO dto) {
        if (dto == null) return null;
        ItemServicoExternoModel entity = new ItemServicoExternoModel();
        entity.setId(dto.getId());
        entity.setAtivo(dto.isActive());
        entity.setQuantidade(dto.getQuantidade());
        entity.setValorUnitario(dto.getValorUnitario());
        entity.setDesconto(dto.getDesconto());
        if (dto.getIdOs() != null) {
            entity.setOrdemDeServico(em.getReference(OrdemDeServicoModel.class, dto.getIdOs()));
        }
        if (dto.getIdSrvExterno() != null) {
            entity.setServicoExterno(em.getReference(ServicoExternoModel.class, dto.getIdSrvExterno()));
        }
        return entity;
    }
}