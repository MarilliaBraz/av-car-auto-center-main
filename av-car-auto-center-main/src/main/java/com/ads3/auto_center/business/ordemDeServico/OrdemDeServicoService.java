package com.ads3.auto_center.business.ordemDeServico;

import com.ads3.auto_center.core.exceptions.RuleValidationException;
import com.ads3.auto_center.core.services.GenericService;
import com.ads3.auto_center.estruturas.FilaAtendimento;
import com.ads3.auto_center.estruturas.OrdenadorOS;
import com.ads3.auto_center.estruturas.TabelaHashEncadeada;
import com.ads3.auto_center.padroes.singleton.GeradorNumeroOS;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class OrdemDeServicoService extends GenericService<OrdemDeServicoModel, IOrdemDeServicoRepository, OrdemDeServicoValidacao> {

    private final FilaAtendimento<OrdemDeServicoModel> filaAtendimento = new FilaAtendimento<>();
    private final TabelaHashEncadeada<OrdemDeServicoModel> indicePorPlaca = new TabelaHashEncadeada<>(16);

    @PostConstruct
    public void inicializarGerador() {
        GeradorNumeroOS.inicializar(repository);
    }

    @Override
    protected void beforeInsert(OrdemDeServicoModel entity) {
        GeradorNumeroOS gerador = GeradorNumeroOS.getInstance();
        entity.setSequencial(gerador.proximoSequencial());
        entity.setNumero(String.format("OS-%d-%04d",
                java.time.LocalDate.now().getYear(), entity.getSequencial()));
    }

    public void enfileirarOS(OrdemDeServicoModel os) {
        filaAtendimento.enfileirar(os);
        if (os.getVeiculo() != null && os.getVeiculo().getPlaca() != null) {
            indicePorPlaca.inserir(os.getVeiculo().getPlaca(), os);
        }
    }

    public OrdemDeServicoModel proximaParaAtendimento() {
        return filaAtendimento.desenfileirar();
    }

    public List<OrdemDeServicoModel> buscarPorPlaca(String placa) {
        return indicePorPlaca.buscar(placa);
    }

    public List<OrdemDeServicoModel> relatorioOrdenado(Comparator<OrdemDeServicoModel> criterio) {
        return OrdenadorOS.ordenar(filaAtendimento.paraLista(), criterio);
    }

    @Override
    protected void beforeUpdate(OrdemDeServicoModel entity) {
        OrdemDeServicoModel existente = findByIdActive(entity.getId());
        if (entity.getDataAbertura() == null) {
            entity.setDataAbertura(existente.getDataAbertura());
        }
    }

    @Override
    protected void beforeDelete(OrdemDeServicoModel entity) {
        if (StatusOS.FINALIZADO.equals(entity.getStatus())) {
            throw new RuleValidationException("os-finalizada", "Não é permitido excluir ordem de serviço finalizada.");
        }
    }
}