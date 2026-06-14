package com.ads3.auto_center.estruturas;

import com.ads3.auto_center.business.ordemDeServico.OrdemDeServicoModel;
import com.ads3.auto_center.business.ordemDeServico.StatusOS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class OrdenadorOS {

    private OrdenadorOS() {}

    // Urgência: EXECUCAO > ORCAMENTO > PAGAMENTO > FINALIZADO
    private static int prioridadeStatus(StatusOS s) {
        return switch (s) {
            case EXECUCAO   -> 0;
            case ORCAMENTO  -> 1;
            case PAGAMENTO  -> 2;
            case FINALIZADO -> 3;
        };
    }

    public static final Comparator<OrdemDeServicoModel> POR_PRIORIDADE =
            Comparator.comparingInt(os -> prioridadeStatus(os.getStatus()));

    public static final Comparator<OrdemDeServicoModel> POR_DATA_ABERTURA =
            Comparator.comparing(OrdemDeServicoModel::getDataAbertura);

    public static final Comparator<OrdemDeServicoModel> POR_VALOR_TOTAL =
            Comparator.comparing(OrdemDeServicoModel::getValorTotal).reversed();

    public static <T> List<T> ordenar(List<T> lista, Comparator<T> comparador) {
        if (lista == null || lista.size() <= 1) {
            return lista == null ? new ArrayList<>() : new ArrayList<>(lista);
        }
        int meio = lista.size() / 2;
        List<T> esquerda = ordenar(new ArrayList<>(lista.subList(0, meio)), comparador);
        List<T> direita  = ordenar(new ArrayList<>(lista.subList(meio, lista.size())), comparador);
        return mesclar(esquerda, direita, comparador);
    }

    private static <T> List<T> mesclar(List<T> esquerda, List<T> direita, Comparator<T> comparador) {
        List<T> resultado = new ArrayList<>(esquerda.size() + direita.size());
        int i = 0, j = 0;
        while (i < esquerda.size() && j < direita.size()) {
            if (comparador.compare(esquerda.get(i), direita.get(j)) <= 0) {
                resultado.add(esquerda.get(i++));
            } else {
                resultado.add(direita.get(j++));
            }
        }
        while (i < esquerda.size()) resultado.add(esquerda.get(i++));
        while (j < direita.size())  resultado.add(direita.get(j++));
        return resultado;
    }
}