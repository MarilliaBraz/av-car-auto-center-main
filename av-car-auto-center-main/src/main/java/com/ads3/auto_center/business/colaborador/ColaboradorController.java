package com.ads3.auto_center.business.colaborador;

import com.ads3.auto_center.core.controllers.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController extends GenericController<ColaboradorModel, ColaboradorDTO, ColaboradorService, ColaboradorMapper> {
}