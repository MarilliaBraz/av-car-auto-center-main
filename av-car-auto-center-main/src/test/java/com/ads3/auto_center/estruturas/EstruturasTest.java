package com.ads3.auto_center.estruturas;

import com.ads3.auto_center.business.ordemDeServico.OrdemDeServicoModel;
import com.ads3.auto_center.business.ordemDeServico.StatusOS;
import com.ads3.auto_center.business.veiculo.VeiculoModel;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstruturasTest {

    // -------------------------------------------------------------------------
    // FilaAtendimento
    // -------------------------------------------------------------------------

    @Test
    void fila_enfileira3_desenfileirarRespeiraFIFO() {
        var fila = new FilaAtendimento<String>();
        fila.enfileirar("A");
        fila.enfileirar("B");
        fila.enfileirar("C");

        assertEquals("A", fila.desenfileirar());
        assertEquals("B", fila.desenfileirar());
        assertEquals("C", fila.desenfileirar());
        assertNull(fila.desenfileirar());
    }

    @Test
    void fila_peek_naoRemoveElemento() {
        var fila = new FilaAtendimento<String>();
        fila.enfileirar("X");

        assertEquals("X", fila.peek());
        assertEquals(1, fila.tamanho());
        assertEquals("X", fila.peek()); // ainda lá
    }

    @Test
    void fila_paraLista_snapshotSemModificarFila() {
        var fila = new FilaAtendimento<Integer>();
        fila.enfileirar(1);
        fila.enfileirar(2);
        fila.enfileirar(3);

        List<Integer> snapshot = fila.paraLista();

        assertEquals(List.of(1, 2, 3), snapshot);
        assertEquals(3, fila.tamanho()); // fila intacta
    }

    @Test
    void fila_iterator_percorreTodosSemConsumir() {
        var fila = new FilaAtendimento<String>();
        fila.enfileirar("P");
        fila.enfileirar("Q");
        fila.enfileirar("R");

        var coletados = new java.util.ArrayList<String>();
        for (String s : fila) coletados.add(s);

        assertEquals(List.of("P", "Q", "R"), coletados);
        assertEquals(3, fila.tamanho()); // não consumiu
    }

    // -------------------------------------------------------------------------
    // OrdenadorOS
    // -------------------------------------------------------------------------

    @Test
    void ordenador_listaNula_retornaListaVazia() {
        List<OrdemDeServicoModel> resultado = OrdenadorOS.ordenar(null, OrdenadorOS.POR_DATA_ABERTURA);
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void ordenador_listaComUmElemento_retornaSemAlterar() {
        var os = osComData(LocalDateTime.now());
        List<OrdemDeServicoModel> resultado = OrdenadorOS.ordenar(List.of(os), OrdenadorOS.POR_DATA_ABERTURA);
        assertEquals(1, resultado.size());
        assertSame(os, resultado.get(0));
    }

    @Test
    void ordenador_porDataAbertura_ordenaCrescente() {
        var base = LocalDateTime.of(2024, 1, 1, 0, 0);
        var os1 = osComData(base.plusDays(2));
        var os2 = osComData(base.plusDays(0));
        var os3 = osComData(base.plusDays(5));
        var os4 = osComData(base.plusDays(1));
        var os5 = osComData(base.plusDays(3));

        List<OrdemDeServicoModel> resultado = OrdenadorOS.ordenar(
                new java.util.ArrayList<>(List.of(os1, os2, os3, os4, os5)),
                OrdenadorOS.POR_DATA_ABERTURA
        );

        for (int i = 0; i < resultado.size() - 1; i++) {
            assertFalse(resultado.get(i).getDataAbertura()
                    .isAfter(resultado.get(i + 1).getDataAbertura()));
        }
    }

    @Test
    void ordenador_estabilidade_mesmadataMantemOrdemRelativa() {
        var data = LocalDateTime.of(2024, 6, 1, 10, 0);
        var osA = osComData(data);
        osA.setObservacoes("A");
        var osB = osComData(data);
        osB.setObservacoes("B");

        List<OrdemDeServicoModel> resultado = OrdenadorOS.ordenar(
                new java.util.ArrayList<>(List.of(osA, osB)),
                OrdenadorOS.POR_DATA_ABERTURA
        );

        assertEquals("A", resultado.get(0).getObservacoes());
        assertEquals("B", resultado.get(1).getObservacoes());
    }

    // -------------------------------------------------------------------------
    // TabelaHashEncadeada
    // -------------------------------------------------------------------------

    @Test
    void hash_buscarChaveInexistente_retornaListaVazia() {
        var tabela = new TabelaHashEncadeada<OrdemDeServicoModel>(16);
        List<OrdemDeServicoModel> resultado = tabela.buscar("ABC1234");
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void hash_duasOSMesmaplaca_buscarRetornaAmbas() {
        var tabela = new TabelaHashEncadeada<OrdemDeServicoModel>(16);
        var os1 = new OrdemDeServicoModel();
        var os2 = new OrdemDeServicoModel();
        tabela.inserir("XYZ9999", os1);
        tabela.inserir("XYZ9999", os2);

        List<OrdemDeServicoModel> resultado = tabela.buscar("XYZ9999");
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(os1));
        assertTrue(resultado.contains(os2));
    }

    @Test
    void hash_remover_eliminaChaveEBuscarRetornaVazio() {
        var tabela = new TabelaHashEncadeada<OrdemDeServicoModel>(16);
        tabela.inserir("AAA0000", new OrdemDeServicoModel());

        assertTrue(tabela.remover("AAA0000"));
        assertTrue(tabela.buscar("AAA0000").isEmpty());
    }

    @Test
    void hash_colisaoForcada_naoCorrompeOutrasChaves() {
        // Usa capacidade 1 para forçar todos no mesmo balde
        var tabela = new TabelaHashEncadeada<String>(1);
        tabela.inserir("PLACA1", "OS-A");
        tabela.inserir("PLACA2", "OS-B");
        tabela.inserir("PLACA1", "OS-C");

        assertEquals(List.of("OS-A", "OS-C"), tabela.buscar("PLACA1"));
        assertEquals(List.of("OS-B"),          tabela.buscar("PLACA2"));
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private OrdemDeServicoModel osComData(LocalDateTime data) {
        var os = new OrdemDeServicoModel();
        os.setDataAbertura(data);
        os.setStatus(StatusOS.ORCAMENTO);
        os.setValorTotal(BigDecimal.ZERO);
        return os;
    }
}