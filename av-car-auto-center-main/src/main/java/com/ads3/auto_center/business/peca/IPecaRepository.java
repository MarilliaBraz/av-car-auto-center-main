package com.ads3.auto_center.business.peca;

import com.ads3.auto_center.core.repositories.IGenericRepository;
import java.util.Optional;

public interface IPecaRepository extends IGenericRepository<PecaModel> {
    boolean existsByCodigoRef(String codigoRef);
    Optional<PecaModel> findByCodigoRef(String codigoRef);
}