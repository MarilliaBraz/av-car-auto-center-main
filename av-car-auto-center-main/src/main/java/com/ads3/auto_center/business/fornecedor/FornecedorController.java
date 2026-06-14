package com.ads3.auto_center.business.fornecedor;

import com.ads3.auto_center.core.controllers.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController extends GenericController<FornecedorModel, FornecedorDTO, FornecedorService, FornecedorMapper> {
}