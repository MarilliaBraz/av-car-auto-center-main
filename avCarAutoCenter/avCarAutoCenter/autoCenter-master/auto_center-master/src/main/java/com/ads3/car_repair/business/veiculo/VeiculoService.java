package com.ads3.car_repair.business.veiculo;

import com.ads3.car_repair.core.exceptions.RuleValidationException;
import com.ads3.car_repair.core.services.GenericService;
import com.ads3.car_repair.core.services.IGenericService;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService extends GenericService<VeiculoModel, IVeiculoRepository, IVeiculoValidation> implements IVeiculoService{
    @Override
    public VeiculoModel buscarPorPlaca(String placa) {

        VeiculoModel veiculo = repository.findByPlaca(placa);

        if (veiculo == null) {
            throw new RuntimeException("Veículo não encontrado.");
        }

        return veiculo;
    }
    @Override
    protected void beforeDelete(VeiculoModel entity) {

        if (!entity.getOrdensServico().isEmpty()) {

            throw new RuleValidationException(
                    "veiculo-com-ordens",
                    "Veículo possui ordens de serviço vinculadas."
            );
        }
    }
}
