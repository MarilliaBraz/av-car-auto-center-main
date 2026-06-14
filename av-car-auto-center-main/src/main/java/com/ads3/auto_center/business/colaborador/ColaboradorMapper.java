package com.ads3.auto_center.business.colaborador;

import com.ads3.auto_center.business.funcaoColaborador.FuncaoColaboradorModel;
import com.ads3.auto_center.core.helpers.IGenericMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ColaboradorMapper implements IGenericMapper<ColaboradorModel, ColaboradorDTO> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public ColaboradorDTO toDto(ColaboradorModel entity) {
        if (entity == null) return null;
        ColaboradorDTO dto = new ColaboradorDTO();
        dto.setId(entity.getId());
        dto.setActive(entity.isAtivo());
        dto.setTelefone(entity.getTelefone());
        dto.setEmail(entity.getEmail());
        dto.setEndereco(entity.getEndereco());
        dto.setNome(entity.getNome());
        dto.setCpf(entity.getCpf());
        dto.setDataAdmissao(entity.getDataAdmissao());
        dto.setSalario(entity.getSalario());
        if (entity.getFuncoes() != null) {
            dto.setIdFuncoes(entity.getFuncoes().stream()
                    .map(FuncaoColaboradorModel::getId)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    @Override
    public ColaboradorModel toEntity(ColaboradorDTO dto) {
        if (dto == null) return null;
        ColaboradorModel entity = new ColaboradorModel();
        entity.setId(dto.getId());
        entity.setAtivo(dto.isActive());
        entity.setTelefone(dto.getTelefone());
        entity.setEmail(dto.getEmail());
        entity.setEndereco(dto.getEndereco());
        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setDataAdmissao(dto.getDataAdmissao());
        entity.setSalario(dto.getSalario());
        if (dto.getIdFuncoes() != null) {
            List<FuncaoColaboradorModel> funcoes = dto.getIdFuncoes().stream()
                    .map(id -> em.getReference(FuncaoColaboradorModel.class, id))
                    .collect(Collectors.toList());
            entity.setFuncoes(funcoes);
        }
        return entity;
    }
}