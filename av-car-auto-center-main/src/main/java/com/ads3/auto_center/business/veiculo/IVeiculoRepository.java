package com.ads3.auto_center.business.veiculo;

import com.ads3.auto_center.core.repositories.IGenericRepository;
import java.util.Optional;

public interface IVeiculoRepository extends IGenericRepository<VeiculoModel> {
    boolean existsByPlaca(String placa);
    Optional<VeiculoModel> findByPlaca(String placa);
}