package com.ads3.car_repair.business.veiculo;

import com.ads3.car_repair.core.repositories.IGenericRepository;

public interface IVeiculoRepository extends IGenericRepository<VeiculoModel> {
    boolean existsByPlaca(String placa);


    VeiculoModel findByPlaca(String placa);
}
