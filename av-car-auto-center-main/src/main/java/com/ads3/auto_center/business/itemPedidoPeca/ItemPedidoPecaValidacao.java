package com.ads3.auto_center.business.itemPedidoPeca;

import com.ads3.auto_center.core.exceptions.FieldValidationException;
import com.ads3.auto_center.core.validations.GenericValidation;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoPecaValidacao extends GenericValidation<ItemPedidoPecaModel, IItemPedidoPecaRepository> {

    @Override
    public void validateFields(ItemPedidoPecaModel entity) {
        super.validateFields(entity);
        if (entity.getQuantidade() == null || entity.getQuantidade() <= 0) {
            throw new FieldValidationException("quantidade", "Quantidade deve ser maior que zero.");
        }
        if (entity.getValorUnitario() == null || entity.getValorUnitario().signum() <= 0) {
            throw new FieldValidationException("valorUnitario", "Valor unitário deve ser maior que zero.");
        }
    }
}