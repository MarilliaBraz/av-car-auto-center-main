package com.ads3.car_repair.business.veiculo;

import com.ads3.car_repair.core.services.GenericService;
import com.ads3.car_repair.core.services.IGenericService;

public interface IVeiculoService extends IGenericService<VeiculoModel,
        IVeiculoRepository,
        IVeiculoValidation> {
    VeiculoModel buscarPorPlaca(String placa);

}
