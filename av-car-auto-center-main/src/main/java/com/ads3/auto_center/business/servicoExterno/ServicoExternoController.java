package com.ads3.auto_center.business.servicoExterno;

import com.ads3.auto_center.core.controllers.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servicos-externos")
public class ServicoExternoController extends GenericController<ServicoExternoModel, ServicoExternoDTO, ServicoExternoService, ServicoExternoMapper> {
}