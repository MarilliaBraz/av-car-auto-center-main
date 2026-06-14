package com.ads3.auto_center.business.itemServicoInterno;

import com.ads3.auto_center.core.controllers.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itens-servico-interno")
public class ItemServicoInternoController extends GenericController<ItemServicoInternoModel, ItemServicoInternoDTO, ItemServicoInternoService, ItemServicoInternoMapper> {
}