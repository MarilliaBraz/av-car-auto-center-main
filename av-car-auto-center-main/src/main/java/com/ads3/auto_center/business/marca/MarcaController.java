package com.ads3.auto_center.business.marca;

import com.ads3.auto_center.core.controllers.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marcas")
public class MarcaController extends GenericController<MarcaModel, MarcaDTO, MarcaService, MarcaMapper> {
}