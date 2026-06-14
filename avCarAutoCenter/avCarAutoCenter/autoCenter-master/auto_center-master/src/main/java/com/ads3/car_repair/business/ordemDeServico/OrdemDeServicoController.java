package com.ads3.car_repair.business.ordemDeServico;

import com.ads3.car_repair.core.controllers.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordens-de-servico")
public class OrdemDeServicoController extends GenericController<OrdemDeServicoModel, OrdemDeServicoDTO, OrdemDeServicoService, OrdemDeServicoMapper> {
}
