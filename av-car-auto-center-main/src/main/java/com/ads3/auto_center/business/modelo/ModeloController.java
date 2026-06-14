package com.ads3.auto_center.business.modelo;

import com.ads3.auto_center.core.controllers.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/modelos")
public class ModeloController extends GenericController<ModeloModel, ModeloDTO, ModeloService, ModeloMapper> {
}