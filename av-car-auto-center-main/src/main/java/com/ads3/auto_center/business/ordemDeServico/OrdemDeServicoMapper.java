package com.ads3.auto_center.business.ordemDeServico;

import com.ads3.auto_center.business.veiculo.VeiculoModel;
import com.ads3.auto_center.core.helpers.IGenericMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class OrdemDeServicoMapper implements IGenericMapper<OrdemDeServicoModel, OrdemDeServicoDTO> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public OrdemDeServicoDTO toDto(OrdemDeServicoModel entity) {
        if (entity == null) return null;
        OrdemDeServicoDTO dto = new OrdemDeServicoDTO();
        dto.setId(entity.getId());
        dto.setActive(entity.isAtivo());
        dto.setNumero(entity.getNumero());
        dto.setDataAbertura(entity.getDataAbertura());
        dto.setDataFechamento(entity.getDataFechamento());
        dto.setStatus(entity.getStatus());
        dto.setValorTotal(entity.getValorTotal());
        dto.setObservacoes(entity.getObservacoes());
        if (entity.getVeiculo() != null) dto.setIdVeiculo(entity.getVeiculo().getId());
        return dto;
    }

    @Override
    public OrdemDeServicoModel toEntity(OrdemDeServicoDTO dto) {
        if (dto == null) return null;
        OrdemDeServicoModel entity = new OrdemDeServicoModel();
        entity.setId(dto.getId());
        entity.setAtivo(dto.isActive());
        entity.setDataAbertura(dto.getDataAbertura());
        entity.setDataFechamento(dto.getDataFechamento());
        entity.setStatus(dto.getStatus());
        entity.setValorTotal(dto.getValorTotal());
        entity.setObservacoes(dto.getObservacoes());
        if (dto.getIdVeiculo() != null) {
            entity.setVeiculo(em.getReference(VeiculoModel.class, dto.getIdVeiculo()));
        }
        return entity;
    }
}