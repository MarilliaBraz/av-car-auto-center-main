package com.ads3.auto_center.business.peca;

import com.ads3.auto_center.core.controllers.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pecas")
public class PecaController extends GenericController<PecaModel, PecaDTO, PecaService, PecaMapper> {
}