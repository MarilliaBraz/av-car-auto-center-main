package com.ads3.auto_center.business.servicoInterno;

import com.ads3.auto_center.core.controllers.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servicos-internos")
public class ServicoInternoController extends GenericController<ServicoInternoModel, ServicoInternoDTO, ServicoInternoService, ServicoInternoMapper> {
}