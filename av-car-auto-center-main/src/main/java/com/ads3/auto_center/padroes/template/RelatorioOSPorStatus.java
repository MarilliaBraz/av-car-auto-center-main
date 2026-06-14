package com.ads3.auto_center.padroes.template;

import com.ads3.auto_center.business.ordemDeServico.IOrdemDeServicoRepository;
import com.ads3.auto_center.business.ordemDeServico.OrdemDeServicoModel;
import com.ads3.auto_center.business.ordemDeServico.StatusOS;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class RelatorioOSPorStatus extends RelatorioTemplate<OrdemDeServicoModel> {

    private final IOrdemDeServicoRepository repository;
    private final StatusOS status;

    public RelatorioOSPorStatus(IOrdemDeServicoRepository repository, StatusOS status) {
        this.repository = repository;
        this.status = status;
    }

    @Override
    protected String getTitulo() {
        return "Ordens de Serviço — Status: " + status.name();
    }

    @Override
    protected List<OrdemDeServicoModel> montarCorpo() {
        return repository.findAllByAtivoTrue(Pageable.unpaged())
                .stream()
                .filter(os -> status.equals(os.getStatus()))
                .toList();
    }
}