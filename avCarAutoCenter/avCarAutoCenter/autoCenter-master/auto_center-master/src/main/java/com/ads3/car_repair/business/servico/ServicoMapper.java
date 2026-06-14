package com.ads3.car_repair.business.servico;

import com.ads3.car_repair.business.ordemDeServico.IOrdemDeServicoRepository;
import com.ads3.car_repair.business.ordemDeServico.OrdemDeServicoModel;
import com.ads3.car_repair.core.exceptions.BusinessException;
import com.ads3.car_repair.core.helpers.IGenericMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ServicoMapper implements IGenericMapper<ServicoModel, ServicoDTO> {

    @Autowired
    private IOrdemDeServicoRepository ordemDeServicoRepository;

    @Override
    public ServicoDTO toDto(ServicoModel entity) {
        if (entity == null) return null;

        ServicoDTO dto = new ServicoDTO();
        dto.setId(entity.getId());
        dto.setActive(entity.isAtivo());
        dto.setOsId(entity.getOs() != null ? entity.getOs().getId() : null);
        dto.setDescricao(entity.getDescricao());
        dto.setDataHoraInicio(entity.getDataHoraInicio());
        dto.setDataHoraTermino(entity.getDataHoraTermino());
        dto.setMecanico(entity.getMecanico());
        dto.setValor(entity.getValor());
        return dto;
    }

    @Override
    public ServicoModel toEntity(ServicoDTO dto) {
        if (dto == null) return null;

        ServicoModel entity = new ServicoModel();
        entity.setId(dto.getId());
        entity.setAtivo(dto.isActive());
        entity.setDescricao(dto.getDescricao());
        entity.setDataHoraInicio(dto.getDataHoraInicio());
        entity.setDataHoraTermino(dto.getDataHoraTermino());
        entity.setMecanico(dto.getMecanico());
        entity.setValor(dto.getValor());

        if (dto.getOsId() != null) {
            OrdemDeServicoModel os = ordemDeServicoRepository.findById(dto.getOsId())
                    .orElseThrow(() -> new BusinessException(
                            "Ordem de Serviço com ID informado não encontrada.", HttpStatus.NOT_FOUND));
            entity.setOs(os);
        }

        return entity;
    }
}
