package com.ads3.auto_center.business.veiculo;

import com.ads3.auto_center.core.services.IGenericService;

public interface IVeiculoService extends IGenericService<VeiculoModel, IVeiculoRepository, IVeiculoValidation> {
    VeiculoModel buscarPorPlaca(String placa);
}