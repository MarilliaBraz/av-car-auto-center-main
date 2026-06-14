package com.ads3.car_repair.business.veiculo;

import com.ads3.car_repair.core.helpers.IGenericMapper;
import org.springframework.stereotype.Component;

@Component
public class VeiculoMapper implements IGenericMapper<VeiculoModel, VeiculoDTO> {

    @Override
    public VeiculoDTO toDto(VeiculoModel entity) {
        if (entity == null) return null;

        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(entity.getId());
        dto.setActive(entity.isAtivo());
        dto.setMarca(entity.getMarca());
        dto.setModelo(entity.getModelo());
        dto.setAnoFabric(entity.getAnoFabric());
        dto.setAnoModelo(entity.getAnoModelo());
        dto.setPlaca(entity.getPlaca());
        dto.setCor(entity.getCor());
        return dto;
    }

    @Override
    public VeiculoModel toEntity(VeiculoDTO dto) {
        if (dto == null) return null;

        VeiculoModel entity = new VeiculoModel();
        entity.setId(dto.getId());
        entity.setAtivo(dto.isActive());
        entity.setMarca(dto.getMarca());
        entity.setModelo(dto.getModelo());
        entity.setAnoFabric(dto.getAnoFabric());
        entity.setAnoModelo(dto.getAnoModelo());
        entity.setPlaca(dto.getPlaca());
        entity.setCor(dto.getCor());
        return entity;
    }
}
