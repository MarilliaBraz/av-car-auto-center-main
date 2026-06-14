package com.ads3.auto_center.padroes.template;

import com.ads3.auto_center.business.ordemDeServico.IOrdemDeServicoRepository;
import com.ads3.auto_center.business.ordemDeServico.OrdemDeServicoModel;
import com.ads3.auto_center.business.ordemDeServico.StatusOS;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public class RelatorioFaturamento extends RelatorioTemplate<OrdemDeServicoModel> {

    private final IOrdemDeServicoRepository repository;

    public RelatorioFaturamento(IOrdemDeServicoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected String getTitulo() {
        return "Faturamento — OS Finalizadas";
    }

    @Override
    protected List<OrdemDeServicoModel> montarCorpo() {
        var finalizadas = repository.findAllByAtivoTrue(Pageable.unpaged())
                .stream()
                .filter(os -> StatusOS.FINALIZADO.equals(os.getStatus()))
                .toList();

        BigDecimal total = finalizadas.stream()
                .map(OrdemDeServicoModel::getValorTotal)
                .filter(v -> v != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Total disponível via rodapé customizado — corpo retorna as OS
        this.totalFaturado = total;
        return finalizadas;
    }

    private BigDecimal totalFaturado = BigDecimal.ZERO;

    @Override
    protected String montarRodape() {
        return "Total faturado: R$ " + totalFaturado + " | Fim do relatório — " + getTitulo();
    }
}