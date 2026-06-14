package com.ads3.auto_center.business.veiculo;

import com.ads3.auto_center.business.cliente.ClienteModel;
import com.ads3.auto_center.business.modelo.ModeloModel;
import com.ads3.auto_center.core.helpers.IGenericMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class VeiculoMapper implements IGenericMapper<VeiculoModel, VeiculoDTO> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public VeiculoDTO toDto(VeiculoModel entity) {
        if (entity == null) return null;
        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(entity.getId());
        dto.setActive(entity.isAtivo());
        dto.setPlaca(entity.getPlaca());
        dto.setAnoFabricacao(entity.getAnoFabricacao());
        dto.setCor(entity.getCor());
        if (entity.getModelo() != null) dto.setIdModelo(entity.getModelo().getId());
        if (entity.getCliente() != null) dto.setIdCliente(entity.getCliente().getId());
        return dto;
    }

    @Override
    public VeiculoModel toEntity(VeiculoDTO dto) {
        if (dto == null) return null;
        VeiculoModel entity = new VeiculoModel();
        entity.setId(dto.getId());
        entity.setAtivo(dto.isActive());
        entity.setPlaca(dto.getPlaca());
        entity.setAnoFabricacao(dto.getAnoFabricacao());
        entity.setCor(dto.getCor());
        if (dto.getIdModelo() != null) {
            entity.setModelo(em.getReference(ModeloModel.class, dto.getIdModelo()));
        }
        if (dto.getIdCliente() != null) {
            entity.setCliente(em.getReference(ClienteModel.class, dto.getIdCliente()));
        }
        return entity;
    }
}