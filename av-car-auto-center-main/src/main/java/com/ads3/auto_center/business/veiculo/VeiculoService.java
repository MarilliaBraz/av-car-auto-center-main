package com.ads3.auto_center.business.veiculo;

import com.ads3.auto_center.core.exceptions.BusinessException;
import com.ads3.auto_center.core.services.GenericService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService extends GenericService<VeiculoModel, IVeiculoRepository, VeiculoValidation> {

    public VeiculoModel buscarPorPlaca(String placa) {
        return repository.findByPlaca(placa)
                .orElseThrow(() -> new BusinessException("Veículo não encontrado para a placa informada.", HttpStatus.NOT_FOUND));
    }
}