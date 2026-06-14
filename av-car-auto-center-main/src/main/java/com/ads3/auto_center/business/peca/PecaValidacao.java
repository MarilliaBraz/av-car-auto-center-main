package com.ads3.auto_center.business.peca;

import com.ads3.auto_center.core.exceptions.FieldValidationException;
import com.ads3.auto_center.core.exceptions.RuleValidationException;
import com.ads3.auto_center.core.validations.GenericValidation;
import org.springframework.stereotype.Component;

@Component
public class PecaValidacao extends GenericValidation<PecaModel, IPecaRepository> {

    @Override
    public void validateFields(PecaModel entity) {
        super.validateFields(entity);
        if (entity.getNomePeca() == null || entity.getNomePeca().isBlank()) {
            throw new FieldValidationException("nomePeca", "Nome da peça é obrigatório.");
        }
        if (entity.getPrecoUnitario() == null || entity.getPrecoUnitario().signum() <= 0) {
            throw new FieldValidationException("precoUnitario", "Preço unitário deve ser maior que zero.");
        }
        if (entity.getEstoqueAtual() == null || entity.getEstoqueAtual() < 0) {
            throw new FieldValidationException("estoqueAtual", "Estoque não pode ser negativo.");
        }
    }

    @Override
    public void validateInsert(PecaModel entity) {
        if (entity.getCodigoRef() != null && !entity.getCodigoRef().isBlank()
                && repository.existsByCodigoRef(entity.getCodigoRef())) {
            throw new RuleValidationException("codigo-ref-duplicado", "Código de referência já cadastrado.");
        }
    }
}