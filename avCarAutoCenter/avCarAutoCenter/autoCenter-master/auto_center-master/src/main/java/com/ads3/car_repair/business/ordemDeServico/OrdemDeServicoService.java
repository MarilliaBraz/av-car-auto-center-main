package com.ads3.car_repair.business.ordemDeServico;

import com.ads3.car_repair.core.exceptions.RuleValidationException;
import com.ads3.car_repair.core.services.GenericService;
import org.springframework.stereotype.Service;

@Service
public class OrdemDeServicoService extends GenericService<OrdemDeServicoModel, IOrdemDeServicoRepository, OrdemDeServicoValidacao> {
    @Override
    protected void beforeDelete(OrdemDeServicoModel entity) {

        if ("FINALIZADA".equals(entity.getStatus())) {

            throw new RuleValidationException(
                    "os-finalizada",
                    "Não é permitido excluir ordem de serviço finalizada."
            );
        }
    }
}
