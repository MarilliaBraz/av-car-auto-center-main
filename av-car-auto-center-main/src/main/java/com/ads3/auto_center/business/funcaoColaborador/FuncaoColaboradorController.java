package com.ads3.auto_center.business.funcaoColaborador;

import com.ads3.auto_center.core.controllers.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcoes-colaborador")
public class FuncaoColaboradorController extends GenericController<FuncaoColaboradorModel, FuncaoColaboradorDTO, FuncaoColaboradorService, FuncaoColaboradorMapper> {
}