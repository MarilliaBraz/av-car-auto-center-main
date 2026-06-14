package com.ads3.auto_center.business.itemPedidoPeca;

import com.ads3.auto_center.core.controllers.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itens-pedido-peca")
public class ItemPedidoPecaController extends GenericController<ItemPedidoPecaModel, ItemPedidoPecaDTO, ItemPedidoPecaService, ItemPedidoPecaMapper> {
}