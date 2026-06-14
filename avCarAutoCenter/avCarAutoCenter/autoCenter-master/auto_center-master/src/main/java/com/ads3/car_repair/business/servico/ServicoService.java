package com.ads3.car_repair.business.servico;

import com.ads3.car_repair.core.exceptions.RuleValidationException;
import com.ads3.car_repair.core.services.GenericService;
import org.springframework.stereotype.Service;

@Service
public class ServicoService extends GenericService<ServicoModel, IServicoRepository, ServicoValidacao> {
    @Override
    protected void beforeDelete(ServicoModel entity) {

        if (entity.getOs() != null) {
            throw new RuleValidationException(
                    "servico-vinculado",
                    "Serviço está vinculado a uma ordem de serviço."
            );
        }
    }
}
