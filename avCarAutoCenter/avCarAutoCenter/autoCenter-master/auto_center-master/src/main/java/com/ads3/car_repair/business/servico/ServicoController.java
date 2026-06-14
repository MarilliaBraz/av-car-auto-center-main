package com.ads3.car_repair.business.servico;

import com.ads3.car_repair.core.controllers.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servicos")
public class ServicoController extends GenericController<ServicoModel, ServicoDTO, ServicoService, ServicoMapper> {
}
