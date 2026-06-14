package com.ads3.auto_center.business.itemPedidoPeca;

import com.ads3.auto_center.business.ordemDeServico.OrdemDeServicoModel;
import com.ads3.auto_center.business.peca.PecaModel;
import com.ads3.auto_center.core.helpers.IGenericMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoPecaMapper implements IGenericMapper<ItemPedidoPecaModel, ItemPedidoPecaDTO> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public ItemPedidoPecaDTO toDto(ItemPedidoPecaModel entity) {
        if (entity == null) return null;
        ItemPedidoPecaDTO dto = new ItemPedidoPecaDTO();
        dto.setId(entity.getId());
        dto.setActive(entity.isAtivo());
        dto.setQuantidade(entity.getQuantidade());
        dto.setValorUnitario(entity.getValorUnitario());
        dto.setDesconto(entity.getDesconto());
        if (entity.getOrdemDeServico() != null) dto.setIdOs(entity.getOrdemDeServico().getId());
        if (entity.getPeca() != null) dto.setIdPeca(entity.getPeca().getId());
        return dto;
    }

    @Override
    public ItemPedidoPecaModel toEntity(ItemPedidoPecaDTO dto) {
        if (dto == null) return null;
        ItemPedidoPecaModel entity = new ItemPedidoPecaModel();
        entity.setId(dto.getId());
        entity.setAtivo(dto.isActive());
        entity.setQuantidade(dto.getQuantidade());
        entity.setValorUnitario(dto.getValorUnitario());
        entity.setDesconto(dto.getDesconto());
        if (dto.getIdOs() != null) {
            entity.setOrdemDeServico(em.getReference(OrdemDeServicoModel.class, dto.getIdOs()));
        }
        if (dto.getIdPeca() != null) {
            entity.setPeca(em.getReference(PecaModel.class, dto.getIdPeca()));
        }
        return entity;
    }
}