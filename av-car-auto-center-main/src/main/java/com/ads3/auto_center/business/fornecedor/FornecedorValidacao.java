package com.ads3.auto_center.business.fornecedor;

import com.ads3.auto_center.core.exceptions.FieldValidationException;
import com.ads3.auto_center.core.exceptions.RuleValidationException;
import com.ads3.auto_center.core.validations.GenericValidation;
import org.springframework.stereotype.Component;

@Component
public class FornecedorValidacao extends GenericValidation<FornecedorModel, IFornecedorRepository> {

    @Override
    public void validateFields(FornecedorModel entity) {
        super.validateFields(entity);
        if (entity.getNomeFornecedor() == null || entity.getNomeFornecedor().isBlank()) {
            throw new FieldValidationException("nomeFornecedor", "Nome do fornecedor é obrigatório.");
        }
    }

    @Override
    public void validateInsert(FornecedorModel entity) {
        if (entity.getCnpj() != null && !entity.getCnpj().isBlank()
                && repository.existsByCnpj(entity.getCnpj())) {
            throw new RuleValidationException("cnpj-fornecedor-duplicado", "CNPJ do fornecedor já cadastrado.");
        }
    }
}