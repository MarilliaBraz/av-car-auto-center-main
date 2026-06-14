package com.ads3.car_repair.business.ordemDeServico;

import com.ads3.car_repair.business.cliente.ClienteModel;
import com.ads3.car_repair.business.cliente.IClienteRepository;
import com.ads3.car_repair.business.veiculo.IVeiculoRepository;
import com.ads3.car_repair.business.veiculo.VeiculoModel;
import com.ads3.car_repair.core.exceptions.BusinessException;
import com.ads3.car_repair.core.helpers.IGenericMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class OrdemDeServicoMapper implements IGenericMapper<OrdemDeServicoModel, OrdemDeServicoDTO> {

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IVeiculoRepository veiculoRepository;

    @Override
    public OrdemDeServicoDTO toDto(OrdemDeServicoModel entity) {
        if (entity == null) return null;

        OrdemDeServicoDTO dto = new OrdemDeServicoDTO();
        dto.setId(entity.getId());
        dto.setActive(entity.isAtivo());
        dto.setClienteId(entity.getClienteModel() != null ? entity.getClienteModel().getId() : null);
        dto.setVeiculoId(entity.getVeiculoModel() != null ? entity.getVeiculoModel().getId() : null);
        dto.setDataEntrada(entity.getDataEntrada());
        dto.setDataSaida(entity.getDataSaida());
        dto.setProblema(entity.getProblema());
        dto.setObservacao(entity.getObservacao());
        return dto;
    }

    @Override
    public OrdemDeServicoModel toEntity(OrdemDeServicoDTO dto) {
        if (dto == null) return null;

        OrdemDeServicoModel entity = new OrdemDeServicoModel();
        entity.setId(dto.getId());
        entity.setAtivo(dto.isActive());
        entity.setDataEntrada(dto.getDataEntrada());
        entity.setDataSaida(dto.getDataSaida());
        entity.setProblema(dto.getProblema());
        entity.setObservacao(dto.getObservacao());

        if (dto.getClienteId() != null) {
            ClienteModel cliente = clienteRepository.findById(dto.getClienteId())
                    .orElseThrow(() -> new BusinessException(
                            "Cliente com ID informado não encontrado.", HttpStatus.NOT_FOUND));
            entity.setClienteModel(cliente);
        }

        if (dto.getVeiculoId() != null) {
            VeiculoModel veiculo = veiculoRepository.findById(dto.getVeiculoId())
                    .orElseThrow(() -> new BusinessException(
                            "Veículo com ID informado não encontrado.", HttpStatus.NOT_FOUND));
            entity.setVeiculoModel(veiculo);
        }

        return entity;
    }
}
