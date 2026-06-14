package com.ads3.auto_center.padroes.factory;

import com.ads3.auto_center.core.domains.BaseModel;
import com.ads3.auto_center.core.dtos.BaseDTO;

public interface IPessoaFactory<T extends BaseModel, D extends BaseDTO> {
    T criar(D dto);
}